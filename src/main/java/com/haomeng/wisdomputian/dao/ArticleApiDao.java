package com.haomeng.wisdomputian.dao;


import com.haomeng.wisdomputian.bean.Article;
import com.haomeng.wisdomputian.bean.PageParam;

import java.util.List;

public interface ArticleApiDao {

    //新建文章
    public int AddArticle(Article article);


    //查询文章信息带经纬度查询 审核通过 不是删除的数据
    public List<Article> SearchLonLatArticle(PageParam pageParam);

}
