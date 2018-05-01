package cn.wodesh.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by TS on 2018/4/14.
 */
public interface IProductService {

    public Object findById(String proid) throws Exception;

    public Object findByCutProduct(Map condition , HttpServletRequest request) throws Exception;
}
