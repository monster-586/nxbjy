package com.dfbz.service;


import com.dfbz.entity.Article;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ArticleService extends tservice<Article> {

    PageInfo<Article> selectByCondition(Map<String, Object> pam);

    int favoriteCount(Integer articleId);

    List<User> getFavorite(Integer articleId);

    Result changeFavorite(Integer userId, Integer articleId);

    int saveArticle(Map<String, Object> map,User user);

    PageInfo<Article> getFavoriteByCondition(Map<String, Object> pam);
}