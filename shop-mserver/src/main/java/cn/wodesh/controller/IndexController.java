package cn.wodesh.controller;
import cn.wodesh.service.IIndexService;
import cn.wodesh.service.IUserService;
import cn.wodesh.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class IndexController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Value("${default.redirect_url}")
    private String redirect_url;

    @Autowired
    private IIndexService indexService;

    @Autowired
    private IUserService userService;

    @Autowired
    private WchatMessage wchatMessage;

    @RequestMapping("/rest/index")
    public String index(@RequestParam String code) throws Exception{
        LOGGER.info("获取openid的code:["+code+"]" );
        StringBuffer index_url = new StringBuffer();
        index_url.append("redirect:").append(redirect_url);
        index_url.append("?code=").append(code);
        return index_url.toString();
    }

    @GetMapping(value = "/message")
    @ResponseBody
    public String wchatChack(@RequestParam String signature,
                             @RequestParam String timestamp,
                             @RequestParam String nonce,
                             String echostr) throws Exception {
        return WchatChackUtil.chackSigner(signature,timestamp,nonce) ? echostr : null;
    }

    @PostMapping(value = "/message", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String service(@RequestBody String body) throws Exception {
        return wchatMessage.message(body);
    }

    @GetMapping("/rest/index/search")
    @ResponseBody
    public Object findByIndex() throws Exception{
        return indexService.findIndex();
    }

    @PostMapping("/rest/index/cutpage")
    @ResponseBody
    public Object findProductIndexCutpage(@RequestBody Map map) throws Exception{
        return indexService.findProductIndexCutpage(map);
    }
}
