package cn.wodesh.controller;

import cn.wodesh.service.IFastMeilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/5/31.
 */
@RestController
public class FastMeilController {


    @Autowired
    private IFastMeilService fastMeilService;

    @GetMapping("/rest/fastmeil/search")
    public Object findFastMeil(String expnumber, String expcode) throws Exception{
        return fastMeilService.findFastMeil(expnumber , expcode);
    }

}
