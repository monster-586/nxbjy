package com.dfbz.service.impl;
import com.dfbz.dao.ArticleMapper;
import com.dfbz.entity.Article;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ArticleServiceImpl extends tservisceIpm<Article> implements ArticleService {
@Autowired
    ArticleMapper articleMapper;
    @Override
    public PageInfo<Article> selectByCondition(Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Article> list= articleMapper.selectByCondition(map);

        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

    @Override
    public int favoriteCount(Integer articleId) {
        return articleMapper.favoriteCount(articleId);
    }

    @Override
    public List<User> getFavorite(Integer articleId) {
        return articleMapper.getFavorite(articleId);
    }


    @Override
    public Result changeFocus(Integer userId, Integer articleId) {
        Result result = new Result();
        List<User> favoriteUser = articleMapper.getFavorite(userId);
        int count = 0;
        for (User user : favoriteUser) {
            if (user.getId() != userId) {
                count++;
            }
        }
        if (count == favoriteUser.size()) {
            articleMapper.insertFavorite(userId,articleId);
            result.setMsg("收藏成功");
        } else if(count != favoriteUser.size()){
            int i = articleMapper.deleteFavorite(userId, articleId);
            result.setMsg("取消收藏");
        }
        return result;
    }

    @Override
    public int saveArticle(Map<String, Object> map,User user) {
        Article article = new Article();
        if(!StringUtils.isEmpty(map.get("title"))){
            article.setTitle((String) map.get("title"));
        }
        if(!StringUtils.isEmpty(map.get("content"))){
            article.setContent((String) map.get("content"));
        }
        article.setPublishDate(new Date());
        article.setPublishRealName(user.getRealName());
        article.setBrowseCount(1000);
        article.setUserId(user.getId());
        return articleMapper.insert(article);
    }

    @Override
    public PageInfo<Article> getFavoriteByCondition(Map<String, Object> map) {

        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 3);
        }
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Article> list= articleMapper.getFavoriteByCondition(map);
        for (Article article : list) {
            article.setFavoriteCount(articleMapper.favoriteCount(article.getId()));
        }
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
