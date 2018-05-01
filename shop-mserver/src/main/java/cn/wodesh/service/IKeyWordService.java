package cn.wodesh.service;

/**
 * Created by TS on 2018/4/29.
 */
public interface IKeyWordService {

    /**
     * body json字串（keyname , userid）
     * @param body
     * @return
     * @throws Exception
     */
    public void save(String body) throws Exception;

    public Object findByHotSearch() throws Exception;
}
