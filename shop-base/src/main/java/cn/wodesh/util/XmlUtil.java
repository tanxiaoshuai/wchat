package cn.wodesh.util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by TS on 2017/8/10.
 */
public class XmlUtil {

    private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public static String JsonToXml(String str , String rootElement) throws Exception {
        JsonNode node = new ObjectMapper().readTree(str);
        String xml = new XmlMapper().writeValueAsString(node);
        return StringUtils.isEmpty(rootElement) ? xml : xml.replace("ObjectNode" , rootElement);
    }

    public static String JsonToXml(String str) throws Exception {
        JsonNode node = new ObjectMapper().readTree(str);
        String xml = new XmlMapper().writeValueAsString(node);
        return xml.replace("<ObjectNode>" , "")
                .replace("</ObjectNode>" , "");
    }
}
