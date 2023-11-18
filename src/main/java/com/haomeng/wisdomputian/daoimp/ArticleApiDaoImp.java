package com.haomeng.wisdomputian.daoimp;

import com.haomeng.wisdomputian.bean.Article;
import com.haomeng.wisdomputian.bean.PageParam;
import com.haomeng.wisdomputian.dao.ArticleApiDao;
import com.haomeng.wisdomputian.mapper.ArticleApiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleApiDaoImp implements ArticleApiDao {

    @Resource
    private ArticleApiMapper articleApiMapper;

    //新建文章
    @Override
    public int AddArticle(Article article) {
        int result = articleApiMapper.AddArticle(article);
        return result;
    }

    //查询文章信息带经纬度查询 审核通过 不是删除的数据
    @Override
    public List<Article> SearchLonLatArticle(PageParam pageParam) {
        return articleApiMapper.SearchLonLatArticle(pageParam);
    }

}
