package cn.wodesh.controller;

import cn.wodesh.bean.KeyWords;
import cn.wodesh.service.IKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TS on 2018/4/28.
 */
@RestController
@RequestMapping("rest")
public class KeyWordsController {

    @Autowired
    private IKeyService keyService;

    @PostMapping("/keywords/save")
    public Object save(@RequestBody KeyWords keyWords) throws Exception{
        return keyService.save(keyWords);
    }

    @GetMapping("/keywords/search/hot")
    public Object findByHotWords() throws Exception {
        return keyService.findByHotWords();
    }
}
