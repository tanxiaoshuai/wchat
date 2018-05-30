package cn.wodesh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class Config {

    @Value("${numbermaxlimit.shopcarmax}")
    private Integer shopcarMax;

    @Value("${numbermaxlimit.todayordermax}")
    private Integer todayOrderMax;

    public Integer getShopcarMax() {
        return shopcarMax;
    }

    public void setShopcarMax(Integer shopcarMax) {
        this.shopcarMax = shopcarMax;
    }

    public Integer getTodayOrderMax() {
        return todayOrderMax;
    }

    public void setTodayOrderMax(Integer todayOrderMax) {
        this.todayOrderMax = todayOrderMax;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new FileSystemResource("config.yml"));//File引入
        yaml.setResources(new ClassPathResource("config.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
