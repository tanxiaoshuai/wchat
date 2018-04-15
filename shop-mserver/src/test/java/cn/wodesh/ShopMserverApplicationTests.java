package cn.wodesh;

import cn.wodesh.bean.Product;
import cn.wodesh.bean.ProductField;
import cn.wodesh.dao.ProductDao;
import cn.wodesh.dao.ProductFieldDao;
import cn.wodesh.dao.sql.TemplateSQL;
import cn.wodesh.dao.util.SqlKeyVal;
import cn.wodesh.util.BeanFactoryUtil;
import cn.wodesh.util.DateUtil;
import cn.wodesh.util.KeyUtil;
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
		Product product = productDao.findById("e9bc9367392c4b2a9743fd0dc96d13cf" , Product.class);
		product.setFieldList(productFieldDao.findBySQLRequireToList(SqlKeyVal.field("pa_proid" , product.getProid()), ProductField.class));
		System.out.println(product);
//		System.out.println(productFieldDao.findById("e9bc9367392c4b2a9743fd0dc96d13cf" , ProductField.class));
//		System.out.println(productDao.findById("e9bc9367392c4b2a9743fd0dc96d13cf" , Product.class));

	}

}
