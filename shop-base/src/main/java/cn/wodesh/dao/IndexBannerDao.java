package cn.wodesh.dao;

import cn.wodesh.bean.IndexBanner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/25.
 */
@Repository
@Mapper
public interface IndexBannerDao extends TemplateDao<IndexBanner>{

}
