package cn.wodesh.service;

/**
 * Created by TS on 2018/4/11.
 */
public interface IUserService {

    public Object login(String code , String token) throws Exception;

    public Object loginTest(String code , String token) throws Exception;

}
