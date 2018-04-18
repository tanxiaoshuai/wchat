package cn.wodesh.controller;

import cn.wodesh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by TS on 2018/4/14.
 */
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/rest/product/{proid}")
    public Object findById(@PathVariable String proid) throws Exception{
        return productService.findById(proid);
    }

    @PostMapping("/rest/product/search")
    public Object findByCutProduct(@RequestBody Map condition) throws Exception{
        return productService.findByCutProduct(condition);
    }
}
