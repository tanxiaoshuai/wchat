package cn.wodesh.controller;
import cn.wodesh.service.IIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Value("${default.redirect_url}")
    private String redirect_url;

    @Autowired
    private IIndexService indexService;

    @RequestMapping("/rest/index")
    public String index(@RequestParam String code) throws Exception{
        LOGGER.info("获取openid的code:["+code+"]" );
        StringBuffer index_url = new StringBuffer();
        index_url.append("redirect:").append(redirect_url);
        index_url.append("?code=").append(code);
        return index_url.toString();
    }

    @GetMapping("/rest/index/search")
    public Object findByIndex() throws Exception{
        return indexService.findIndex();
    }
}
