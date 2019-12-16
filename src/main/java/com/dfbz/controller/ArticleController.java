package com.dfbz.controller;

import com.dfbz.entity.Article;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manager/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("list")
    @ResponseBody
    public PageInfo<Article> selectByCondition(@RequestBody Map<String, Object> map) {
        PageInfo<Article> pageInfo = articleService.selectByCondition(map);
        return pageInfo;
    }

    @RequestMapping("articleDetail")
    @ResponseBody
    public Result ArticleDetail(Integer aId, HttpSession session) {
        Result result = new Result();
        Integer userId = (Integer) session.getAttribute("userId");
        Article article = articleService.selectByPrimaryKey(aId);
        article.setFavoriteCount(articleService.favoriteCount(aId));
        List<User> userList = articleService.getFavorite(aId);
        article.setUserList(userList);
        for (User user : userList) {
            if (user.getId() == userId) {
                article.setFocusType(1);
            }
        }
        result.setArticle(article);
        return result;
    }

    @RequestMapping("changeFavorite")
    @ResponseBody
    public Result changeFavorite(Integer articleId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Result result = articleService.changeFavorite(userId, articleId);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public Result saveArticle(@RequestBody Map<String, Object> map,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        Result result = new Result();
        result.setMsg("保存失败");
        int i=articleService.saveArticle(map,user);
        if(i>0){
            result.setMsg("保存成功");
        }
        return result;
    }

    @RequestMapping("myFavorite")
    @ResponseBody
    public PageInfo<Article> getFavoriteByCondition(@RequestBody Map<String, Object> map, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        map.put("uId",userId);
        System.out.println(map);
        PageInfo<Article> pageInfo = articleService.getFavoriteByCondition(map);
        return pageInfo;
    }
}
