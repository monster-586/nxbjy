package com.dfbz.dao;

import com.dfbz.entity.Article;
import com.dfbz.entity.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends Mapper<Article> {

    @SelectProvider(type = ArticleSqlProvider.class, method = "selectByCondition")
    List<Article> selectByCondition(Map<String, Object> pam);

    @Select("select count(1) favoriteCount from favorite where a_id=#{ArticleId}")
    int favoriteCount(Integer ArticleId);

    @Select(" select us.* from `user` us " +
            " left join favorite fa " +
            " on fa.u_id=us.id " +
            " where fa.a_id=#{articleId} ")
    List<User> getFavorite(Integer articleId);

    @SelectProvider(type = ArticleSqlProvider.class, method = "getFavoriteByCondition")
    List<Article> getFavoriteByCondition(Map<String, Object> pam);

    @Delete(" delete from favorite " +
            " where a_id=#{articleId} " +
            " and u_id=#{userId} ")
    int deleteFavorite(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    @Insert("INSERT INTO `favorite`(`u_id`, `a_id`) VALUES (#{userId}, #{articleId}) ")
    int insertFavorite(@Param("userId") Integer userId, @Param("articleId") Integer articleId);
}