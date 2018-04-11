package cn.wodesh.redis;

/**
 * Created by TS on 2018/3/3.
 */
public interface IRedisListenerService {

    public void redisOnMessage(String key) throws Exception;
}
