package com.four.controller;

import com.four.domain.Blog;
import com.four.domain.Msg;
import com.four.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理CRUD请求
 */
@Controller
public class BlogController {


    @Autowired
    BlogService blogService;





    @RequestMapping("/blogs")
    @ResponseBody
    public Msg getBlogsWithJson(@RequestParam(value="pn", defaultValue = "1") Integer pn){
        //引入PageHelper 分页插件
        PageHelper.startPage(pn,5);
        List<Blog> emps =  blogService.selectAll();
        //将pageinfo交给页面,5连续显示页面
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo", page);
    }

//        @RequestMapping("/blogs")
//        public String getBlogs(@RequestParam(value="pn", defaultValue = "1") Integer pn, Model model){
//
//            //引入PageHelper 分页插件
//            PageHelper.startPage(pn,5);
//            List<Blog> blogs = blogService.selectAll() ;
//            for(Blog blog:blogs){
//                System.out.println(blog.toString());
//            }
//            //将pageinfo交给页面,5连续显示页面
//            PageInfo pageInfo = new PageInfo(blogs,5);
//            model.addAttribute("pageInfo", pageInfo);
//
//            return "blogs";
//        }
}
