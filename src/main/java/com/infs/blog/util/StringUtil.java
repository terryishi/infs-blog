package com.infs.blog.util;

import com.infs.blog.conf.properties.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Lexi
 * @Date: 2023/05/05
 */
@Component
public class StringUtil {

    @Autowired
    private SiteConfig siteConfig;

    // email正则
    public String emailRegex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    // url正则
    public String urlRegex = "^((https|http)?:\\/\\/)[^\\s]+";
    // 用户名正则
    public String usernameRegex = "[a-z0-9A-Z]{2,16}";
    // 密码正则
    public String passwordRegex = "[a-z0-9A-Z]{6,32}";

    public boolean check(String text, String regex) {
        if (StringUtils.isEmpty(text)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            return matcher.matches();
        }
    }

    /*public List<String> sectionNames() {
        return siteConfig.getSections().stream()
                .map(section -> section.get("name"))
                .collect(Collectors.toList());
    }

    public List<String> sectionValues() {
        return siteConfig.getSections().stream()
                .map(section -> section.get("value"))
                .collect(Collectors.toList());
    }*/

    public String listToString(List<String> list, String sep) {
        if (list == null || list.size() == 0) return null;
        StringBuilder str = new StringBuilder();
        for (String s: list) {
            str.append(s).append(sep);
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}
