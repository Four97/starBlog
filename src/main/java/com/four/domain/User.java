package com.four.domain;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer userid;

    private String username;

    private Integer headpic;

    private String md5password;

    private List<Blog> blogs;

    public User() {
    }

    public User(Integer userid, String username, Integer headpic, String md5password) {
        this.userid = userid;
        this.username = username;
        this.headpic = headpic;
        this.md5password = md5password;
    }

    public User(Integer userid, String username, Integer headpic, String md5password, List<Blog> blogs) {
        this.userid = userid;
        this.username = username;
        this.headpic = headpic;
        this.md5password = md5password;
        this.blogs = blogs;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getHeadpic() {
        return headpic;
    }

    public void setHeadpic(Integer headpic) {
        this.headpic = headpic;
    }

    public String getMd5password() {
        return md5password;
    }

    public void setMd5password(String md5password) {
        this.md5password = md5password == null ? null : md5password.trim();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userid.equals(user.userid) &&
                Objects.equals(username, user.username) &&
                Objects.equals(headpic, user.headpic) &&
                Objects.equals(md5password, user.md5password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, headpic, md5password);
    }
}