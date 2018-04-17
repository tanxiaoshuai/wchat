package cn.wodesh.service;

/**
 * Created by TS on 2018/4/14.
 */
public interface IProductService {

    public Object findById(String proid) throws Exception;

    public Object findByCutProduct(String page , String size) throws Exception;
}
