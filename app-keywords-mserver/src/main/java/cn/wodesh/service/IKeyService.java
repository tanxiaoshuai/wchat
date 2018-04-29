package cn.wodesh.service;


import cn.wodesh.bean.KeyWords;

/**
 * Created by TS on 2018/4/28.
 */
public interface IKeyService {

    public Object save(KeyWords keyWords) throws Exception;

    public Object findByHotWords(Integer size) throws Exception;

}
