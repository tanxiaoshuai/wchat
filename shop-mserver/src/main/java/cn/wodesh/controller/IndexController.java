package cn.wodesh.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class IndexController {

    @RequestMapping("/rest/index")
    public String index(@RequestParam String code) throws Exception{
        System.out.println(code);
        return "redirect:https://www.wodesh.cn:8088/api";
    }
}
