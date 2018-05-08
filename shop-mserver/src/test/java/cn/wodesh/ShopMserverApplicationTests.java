package cn.wodesh;

import cn.wodesh.bean.*;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.*;
import cn.wodesh.dao.sql.TemplateSQL;
import cn.wodesh.dao.util.SqlKeyVal;
import cn.wodesh.rabbitmq.RabbitMqSender;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.util.BeanFactoryUtil;
import cn.wodesh.util.DateUtil;
import cn.wodesh.util.KeyUtil;
import cn.wodesh.util.PayUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopMserverApplicationTests {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductFieldDao productFieldDao;

	@Autowired
	private ProductTpyeDao productTpyeDao;

	@Autowired
	private TypeChildDao typeChildDao;

	@Autowired
	private ShopCarDao shopCarDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private IndexProductDao indexProductDao;

	@Autowired
	private RabbitMqSender rabbitMqSender;

	@Autowired
	private PayUtil payUtil;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void contextLoads() throws Exception {
//		Product product = BeanFactoryUtil.getBeanByClass(Product.class);
//		product.setProid(KeyUtil.uuid());
//		product.setCreatetime(DateUtil.longForTime(System.currentTimeMillis() , DateUtil.YEARTOSS));
//		product.setDescribe("透真隔离霜持久补水保湿打底遮瑕提亮液隐形毛孔裸妆紫色妆前乳");
//		product.setDiscount(8);
//		product.setImgs("[\"http://img.zcool.cn/community/013f5958c53a47a801219c772a5335.jpg@900w_1l_2o_100sh.jpg\"]");
//		product.setPrice(1000);
//		product.setProname("隔离霜");
//		product.setStatus(1);
//		product.setType(0);
//		productDao.save(product);
//		Product product = productDao.findById("e9bc9367392c4b2a9743fd0dc96d13cf" , Product.class);
//		product.setFieldList(productFieldDao.findBySQLRequireToList(SqlKeyVal.field("pa_proid" , product.getProid()), ProductField.class));
//		System.out.println(product);
//		System.out.println(productFieldDao.findById("e9bc9367392c4b2a9743fd0dc96d13cf" , ProductField.class));
//		System.out.println(productDao.findById("e9bc9367392c4b2a9743fd0dc96d13cf" , Product.class));

//		System.out.println(JSONArray.toJSONString(productTpyeDao.findByProductTypeList(ProductTpye.class)));
//		System.out.println(JSONArray.toJSONString(typeChildDao.findByTypeChildList("24d7c5873842d689d7d977a3a3c4e432")));
//		int a = productDao.updateProductClick();
//		System.out.println(a);

//		System.out.println(JSONArray.toJSONString(shopCarDao.findShopCarList("758d320555094e438dd374e7febc33e9")));

//		System.out.println(orderDao.findByOrderId("2018042001549322756"));
//		System.out.println(orderDao.findById("2018042001549322756" , Order.class));
//		String str = "{\n" +
//				"  \"orderid\": \"2018042001549322757\",\n" +
//				"  \"createtime\": \"2018-04-20 23:13:23\",\n" +
//				"  \"paytime\": \"2018-04-20 23:15:24\",\n" +
//				"  \"address\": \"四川成都新都区保利紫荆话语\",\n" +
//				"  \"tel\": \"18080283924\",\n" +
//				"  \"receivename\": \"谭帅\",\n" +
//				"  \"paytype\": \"1\",\n" +
//				"  \"userid\": \"758d320555094e438dd374e7febc33e9\",\n" +
//				"  \"cash\": 1600,\n" +
//				"  \"freight\": 0,\n" +
//				"  \"status\": 5,\n" +
//				"  \"statusinfo\": \"交易关闭\"\n" +
//				"}";
//		Order order = JSONObject.parseObject(str , Order.class);
//		orderDao.save(order);
//		System.out.println(indexProductDao.findIndexProductList());
	}

	@Test
	public void payTest() throws Exception {
//		System.out.println(payUtil.Pay("owF-Kw_dNmnrDON7ZGz8VDP3p7k4" , "1" , KeyUtil.uuid()));
//		System.out.println(productFieldDao.updateStock("1aa57704ddad449caacb265fcba52290" , 1));
//		System.out.println(shopCarDao.findShopCarBean("4d325c102ff8ba5dd3d95ae703e4ce52"));
//		AddressDao addressDao = BeanFactoryUtil.getBeanByClass(AddressDao.class);
//		System.out.println(addressDao.findUserToDefualtAddress("758d320555094e438dd374e7febc33e9" , 1));
//		rabbitMqSender.send(new JSONObject().fluentPut("key" , "妈妈，哪个男生为什么有小鸡鸡呀"));
		redisUtil.set(KeyUtil.orderNoPayKey("27c2e984cdc84ec685e3ffcfbb303dc6"), null , AppConfig.ORDER_NO_PAY_OUT_TIME);

	}

}
