package com.infs.blog.controller;

import com.infs.blog.bean.Result;
import com.infs.blog.exception.ApiAssert;
import com.infs.blog.model.Article;
import com.infs.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @GetMapping(value = "/article/{id}")
    private Result detail(@PathVariable Integer id){
        ApiAssert.notNull(id,"ID不能为空");
        Article article = articleService.findById(id);
        ApiAssert.notNull(article,"文章不存在");
        //更新文章的点击次数
        article.setViewCount(article.getViewCount() + 1);
        articleService.update(article);
        return Result.success(article);
    }

    /**
     * 新增文章
     * @param article
     * @return
     */
    @PostMapping(value = "/article")
    public Result save(@RequestBody Article article){
        ApiAssert.notNull(article,"对象为空");
        if (StringUtils.isEmpty(article.getArticleUrl())) {
            article.setShowContent(Boolean.TRUE);
        } else {
            article.setShowContent(Boolean.FALSE);
        }
        article.setCreateDate(new Date());
        article = articleService.save(article);
        return Result.success(article);
    }
}
