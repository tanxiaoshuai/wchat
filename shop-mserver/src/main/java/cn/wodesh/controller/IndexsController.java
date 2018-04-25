package cn.wodesh.controller;

import cn.wodesh.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/4/25.
 */
@RestController
public class IndexsController {

    @Autowired
    private IIndexService indexService;

    @GetMapping("/rest/index/search")
    public Object findByIndex() throws Exception{
        return indexService.findIndex();
    }

}
