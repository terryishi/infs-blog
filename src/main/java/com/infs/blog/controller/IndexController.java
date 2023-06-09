package com.infs.blog.controller;

import com.infs.blog.bean.Page;
import com.infs.blog.conf.properties.SiteConfig;
import com.infs.blog.bean.Result;
import com.infs.blog.exception.ApiAssert;
import com.infs.blog.model.AccessToken;
import com.infs.blog.model.Article;
import com.infs.blog.model.User;
import com.infs.blog.service.AccessTokenService;
import com.infs.blog.service.ArticleService;
import com.infs.blog.service.ThemeService;
import com.infs.blog.service.UserService;
import com.infs.blog.util.JwtTokenUtil;
import com.infs.blog.util.StringUtil;
import com.infs.blog.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@RestController
public class IndexController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private StringUtil stringUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @GetMapping(value = "/articles")
    private Result articles(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        Page<Article> page = articleService.page(pageNo, siteConfig.getPageSize());
        return Result.success(page);
    }

    /**
     * 热门文章
     * @return
     */
    @GetMapping(value = "/articles/hot")
    private Result hotNews(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Page<Article> page = articleService.pageByWeight(pageNo, pageSize);
        return Result.success(page);
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        ApiAssert.notEmpty(username, "Please enter your username");
        ApiAssert.notEmpty(password, "Please enter your password");
        ApiAssert.isTrue(stringUtil.check(username,stringUtil.usernameRegex),"User name can only be entered [0-9a-zA-Z], length 4-16 bits");
        ApiAssert.isTrue(stringUtil.check(password,stringUtil.passwordRegex),"Password can only be entered [0-9a-zA-Z], length 6-32 bits");
        user = userService.findUserByName(username);
        ApiAssert.isNull(user,"Username already exists");
        // 保存用户
        User user1 = userService.create(username, password, email);
        // 保存Token
//        AccessToken accessToken = accessTokenService.create(jwtTokenUtil.generateToken(user.getUsername()), user.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user1.getUsername());
        map.put("avatar",user1.getAvatar());
//        map.put("token",accessToken.getToken());
        return Result.success(map);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(user);
        ApiAssert.notEmpty(username, "Please enter your username");
        ApiAssert.notEmpty(password, "Please enter your password");
        User user1 = userService.findUserByName(username);
        System.out.println(user1);
        ApiAssert.notNull(user, "User does not exist.");
        ApiAssert.isTrue(user1.getPassword().equals(password), "Incorrect password");
//        AccessToken accessToken = accessTokenService.getByUserId(user.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user1.getUsername());
        map.put("avatar",user1.getAvatar());
//        map.put("token",accessToken.getToken());
        return Result.success(map);
    }
}
