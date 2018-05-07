package cn.wodesh.config;

public class AppConfig {

    public final static String TOKEN_PREFIX = "APP_TOKEN_";
    public final static String OUT_TRADE_NO ="OUT_TRADE_NO_";
    public final static String INDEX_PAGE_STAYLE_KEY = "INDEX_PAGE_STAYLE_KEY_PHONE";
    public final static String PRODUCT_TYPE_LIST_KEY = "PRODUCT_TYPE_LIST_KEY_PHONE";
    public final static Long REDIS_TOKEN_OUT_TIME = 30 * 60L;
    public final static Long REDIS_SHOPCAR_BY_ORDER_OUT_TIME = 5 * 60L;


    /********************************************************快递配置*************************************************/
    public static final String EBUSINESSID = "1280517";
    public static final String APPKEY = "b5719362-fe0a-4fb2-a2be-5698b6c6559d";
    public static final String FAST_MAILE_URL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

    /*******************************************************自己服务调用**********************************************/
    public static final String KEYWORDS_SAVE ="/app-keywords-mserver/rest/keywords/save"; //添加搜索关键词
    public static final String KEYWORDS_SEARCH = "/app-keywords-mserver/rest/keywords/search/hot/"; //查询热词

}
