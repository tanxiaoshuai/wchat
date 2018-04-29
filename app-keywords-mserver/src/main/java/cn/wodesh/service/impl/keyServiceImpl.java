package cn.wodesh.service.impl;

import cn.wodesh.bean.KeyWords;
import cn.wodesh.dao.KeyWordsDao;
import cn.wodesh.service.IKeyService;
import cn.wodesh.util.DateUtil;
import cn.wodesh.util.KeyUtil;
import cn.wodesh.util.ParamValidateUtil;
import cn.wodesh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TS on 2018/4/28.
 */
@Service
public class keyServiceImpl implements IKeyService{

    @Value("${hotwords_limit_time}")
    private Long hotwords_limit_time;

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
    public Object findByHotWords(Integer size) throws Exception {
        ParamValidateUtil.notNull(size , "获取数据条数不能为空");
        long e = System.currentTimeMillis();
        long s = e - 24 * 60 * 60 * 1000L * hotwords_limit_time;
        List list = keyWordsDao.findByHotWords(size , DateUtil.longForTime(s , DateUtil.YEARTOSS)
                , DateUtil.longForTime(e , DateUtil.YEARTOSS));
        return ResultUtil.success(list);
    }
}
