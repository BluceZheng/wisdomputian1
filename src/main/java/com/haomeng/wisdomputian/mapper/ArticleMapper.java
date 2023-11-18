package com.haomeng.wisdomputian.mapper;

import com.haomeng.wisdomputian.bean.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    //新建文章
    public int AddArticle(Article article);

    //查找文章信息
    public List<Article> SearchArticles(Article article);

    //查询文章信息带经纬度查询
    public List<Article> SearchLonLatArticle(Article article);

}
