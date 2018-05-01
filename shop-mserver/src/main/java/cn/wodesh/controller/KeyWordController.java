package cn.wodesh.controller;

import cn.wodesh.service.IKeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/4/29.
 */
@RestController
@RequestMapping("rest")
public class KeyWordController {

    @Autowired
    private IKeyWordService keyWordService;

    @GetMapping("/keyword/search")
    public Object findByKeyWordSearch() throws Exception{
        return keyWordService.findByHotSearch();
    }

}
