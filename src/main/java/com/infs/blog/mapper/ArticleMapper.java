package com.infs.blog.mapper;

import com.infs.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/15
 */
@Mapper
public interface ArticleMapper {

    /**
     * 根据主题、作者获取文章
     * @param themeName
     * @param author
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Article> findArticle(@Param("themeName") String themeName,
                              @Param("author") String author,
                              @Param("pageNo") Integer pageNo,
                              @Param("pageSize") Integer pageSize
    );

    /**
     * 查询weight最高的文章
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Article> selectArticleByWeight(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 根据ID查询文章
     * @param id
     * @return
     */
    Article selectById(@Param("id") Integer id);

    /**
     * 根据主题、作者统计文章数量
     * @param themeName
     * @param author
     * @return
     */
    int countArticle(@Param("themeName") String themeName,
                     @Param("author") String author
    );

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateByPrimaryKey(Article article);

    /**
     * 新增文章
     * @param article
     * @return
     */
    int insert(Article article);
}
