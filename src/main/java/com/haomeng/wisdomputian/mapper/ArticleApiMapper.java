package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Article;
import com.haomeng.wisdomputian.bean.PageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleApiMapper {

    //新建文章
    public int AddArticle(Article article);


    //查询文章信息带经纬度查询 审核通过 不是删除的数据
    public List<Article> SearchLonLatArticle(PageParam pageParam);

}
