package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Article;
import com.haomeng.wisdomputian.dao.ArticleDao;
import com.haomeng.wisdomputian.mapper.ArticleMapper;
import com.haomeng.wisdomputian.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleDaoImp implements ArticleDao {

    @Resource
    private ArticleMapper articleMapper;

    //新建文章
    @Override
    public int AddArticle(Article article) {
        int result = articleMapper.AddArticle(article);
        return result;
    }

    //查找文章信息
    @Override
    public List<Article> SearchArticles(Article article) {
        String id = article.getId();
        if ("".equals(id) || id == null || id == "") {
            id = UUIDUtil.getNoLineUUID();
            article.setId(id);
        }
        List<Article> articleList = articleMapper.SearchArticles(article);
        return articleList;
    }

    //查询文章信息带经纬度查询
    @Override
    public List<Article> SearchLonLatArticle(Article article){

        return null;
    }
}
