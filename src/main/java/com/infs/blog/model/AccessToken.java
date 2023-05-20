package com.infs.blog.model;

import java.util.Date;

/**
 * @Author: Lexi
 * @Date: 2023/05/05
 */
public class AccessToken {

    private Integer accessTokenId;

    private String token;

    private Integer userId;

    private Date createDate;

    public Integer getAccessTokenId() {
        return accessTokenId;
    }

    public void setAccessTokenId(Integer accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessTokenId=" + accessTokenId +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                ", createDate=" + createDate +
                '}';
    }
}
