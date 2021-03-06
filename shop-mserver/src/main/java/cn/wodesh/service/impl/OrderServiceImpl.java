package cn.wodesh.service.impl;

import cn.wodesh.bean.Order;
import cn.wodesh.bean.ShopCar;
import cn.wodesh.bean.User;
import cn.wodesh.config.AppConfig;
import cn.wodesh.config.ResultInfo;
import cn.wodesh.config.StatusConfig;
import cn.wodesh.dao.OrderDao;
import cn.wodesh.dao.ProductFieldDao;
import cn.wodesh.dao.ShopCarDao;
import cn.wodesh.exception.FinalException;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IOrderService;
import cn.wodesh.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/21.
 */
@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProductFieldDao productFieldDao;

    @Autowired
    private ShopCarDao shopCarDao;

    @Autowired
    private PayUtil payUtil;

    @Override
    public Object findByOrderId(String orderid) throws Exception {
        Order order = orderDao.findByOrderId(orderid);
        return ResultUtil.success(order);
    }

    @Override
    @Transactional
    public Object addOrder(Map body) throws Exception {
        String outtradeno = (String) body.get("out_trade_no");
        outtradeno = KeyUtil.outTradeNoKey(outtradeno);
        JSONObject address = (JSONObject) JSONObject.toJSON(body.get("address"));
        String payEntrance = (String) body.get("payentrance");

        ParamValidateUtil.notNull(payEntrance, "支付入口类型不能为空");
        if(!redisUtil.exists(outtradeno))
            throw new FinalException(ResultInfo.SHOPCAR_BY_ORDER_OUT_TIME);
        Map map = (Map) redisUtil.get(outtradeno);
        List<ShopCar> shopCars = (List<ShopCar>) map.get("products");
        for(int i = 0 ; i < shopCars.size() ; i++){
            ShopCar sp = shopCars.get(i);
            int a = productFieldDao.updateStock(sp.getFieldid() ,
                    sp.getNumber());
            if(a == 0)
                throw new FinalException(ResultInfo.SHOPCAR_CHOICE_ERROR.setMsg(
                        new StringBuffer().append("【商品").append(i+1).append("】")
                        .append("库存不足或已售完，请重新选择").toString()
                ));
        }
        redisUtil.remove(outtradeno);
        String addressinfo = address.getString("addressinfo");
        String tel = address.getString("tel");
        String receivename = address.getString("receivename");
        String payid = KeyUtil.uuid();
        User user = TokenUtil.tokenGetUser();
        for(ShopCar s : shopCars){
            Order order = new Order();
            order.setOrderid(OrderId.getOrderIdByUUId());
            order.setAddress(addressinfo);
            order.setTel(tel);
            order.setReceivename(receivename);
            order.setPrice(WchatUtil.CashFormatInt(s.getPrice())+"");
            order.setFreight(WchatUtil.CashFormatInt(s.getFreight())+"");
            order.setCreatetime(DateUtil.currentTime(DateUtil.YEARTOSS));
            order.setNumber(s.getNumber());
            order.setPaytype(1);
            order.setPaycash((WchatUtil.CashFormatInt(s.getPrice()) *
                    s.getNumber()) + WchatUtil.CashFormatInt(s.getFreight())+"");
            order.setUserid(user.getUserid());
            order.setStatus(1);
            order.setPaid(s.getFieldid());
            order.setPayid(payid);
            order.setPaystatus(StatusConfig.SENDPAY);
            order.setPayconfirmtype(StatusConfig.NO_CONFIRM);
            orderDao.save(order);
            if(s.getShopcarid() != null)
                shopCarDao.deleteById(s.getShopcarid() , ShopCar.class);
        }
        String res = payUtil.Pay(user.getOpenid() ,
                WchatUtil.CashFormatInt(map.get("cashCount").toString())+"" , payid , payEntrance);
        redisUtil.set(KeyUtil.orderNoPayKey(payid), null , AppConfig.ORDER_NO_PAY_OUT_TIME);
        return ResultUtil.success(JSONObject.parseObject(res));
    }

    @Override
    public Object findByOrderCutPage(Map body) throws Exception {
        JSONObject object = (JSONObject) JSON.toJSON(body);
        Long page = object.getLong("page");
        Integer size = object.getInteger("size");
        String status = object.getString("status");
        body.clear();
        body.put("size" , size);
        body.put("startpage" , (page - 1) * size);
        body.put("status" , status);
        body.put("userid" , TokenUtil.tokenGetUser().getUserid());
        List<Order> list = orderDao.findByOrderPage(body);
        for(Order o : list){
            String orderNoPayId = KeyUtil.orderNoPayKey(o.getPayid());
            o.setStatusinfo(StatusConfig.ORDERSTATUS.get(o.getStatus()));
            Long time = redisUtil.getExpire(orderNoPayId);
            o.orderFormat(o);
            if(time > 0)
                o.setOrderlimittime(time.toString());
        }
        return ResultUtil.success(list);
    }

    @Override
    public Object selectOrderCount() throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("o_userid = '").append(TokenUtil.tokenGetUser().getUserid())
                .append("' and o_status = ");
        long noPay = orderDao.findBySQLRequireToNumber(new StringBuffer().append(sql).append(1).toString(),Order.class);
        long noSendProduct = orderDao.findBySQLRequireToNumber(new StringBuffer().append(sql).
                append(2).toString(),Order.class);
        long noReceiveProduct = orderDao.findBySQLRequireToNumber(new StringBuffer().
                append(sql).append(3).toString(),Order.class);
        return ResultUtil.success(new JSONObject().fluentPut("noPay" , noPay)
        .fluentPut("noSendProduct" , noSendProduct)
        .fluentPut("noReceiveProduct" , noReceiveProduct));
    }

    @Override
    public Object deleteOrderId(String orderid) throws Exception {
        orderDao.updateStatus(orderid , 0);
        return ResultUtil.success();
    }

    @Override
    public Object receiveProduct(String orderid) throws Exception {
        orderDao.updateStatus(orderid , 4);
        return ResultUtil.success();
    }

}
