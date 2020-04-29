package com.four.domain;

import java.util.Date;
import java.util.Objects;

public class Blog {
    private Integer blogid;

    private Integer userid;

    private Date publishtime;

    private String content;

    public Blog() {
    }

    public Blog(Integer blogid, Integer userid, Date publishtime, String content) {
        this.blogid = blogid;
        this.userid = userid;
        this.publishtime = publishtime;
        this.content = content;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return blogid.equals(blog.blogid) &&
                userid.equals(blog.userid) &&
                Objects.equals(publishtime, blog.publishtime) &&
                Objects.equals(content, blog.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogid, userid, publishtime, content);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogid=" + getBlogid() +
                ", userid=" + getUserid() +
                ", publishtime=" + getPublishtime() +
                ", content='" + getContent() + '\'' +
                '}';
    }
}