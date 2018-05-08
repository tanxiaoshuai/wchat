package cn.wodesh.service.impl;

import cn.wodesh.bean.KeyWords;
import cn.wodesh.dao.KeyWordsDao;
import cn.wodesh.service.IKeyService;
import cn.wodesh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TS on 2018/4/28.
 */
@Service
public class KeyServiceImpl implements IKeyService{


    @Autowired
    private KeyWordsDao keyWordsDao;

    @Override
    public Object save(KeyWords keyWords) throws Exception {
        keyWords.setKeyid(KeyUtil.UUID());
        keyWords.setCreatetime(DateUtil.forTime(DateUtil.YEARTOSS));
        keyWordsDao.save(keyWords);
        return ResultUtil.success();
    }

    @Override
    public Object findByHotWords() throws Exception {
        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
        List list = (List) redisUtil.get(Timer.APP_PRODUCT_KEYWORDS_SEARCH);
        return ResultUtil.success(list);
    }
}
