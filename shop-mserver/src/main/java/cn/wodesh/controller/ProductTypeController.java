package cn.wodesh.controller;

import cn.wodesh.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/4/19.
 */
@RestController
public class ProductTypeController {

    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping("/rest/producttype/search")
    public Object findByProductType () throws Exception{
        return productTypeService.findByProductType();
    }
}
