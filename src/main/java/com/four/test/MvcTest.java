package com.four.test;


import com.four.domain.Blog;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MvcTest {
    @Autowired
    WebApplicationContext context;


    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc =  MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/blogs").param("pn", "1")).andReturn();


        MockHttpServletRequest request = result.getRequest();
        System.out.println(request==null);
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
        System.out.println(pi==null);
        System.out.println("当前页码"+pi.getPageNum());
        System.out.println("总页码"+pi.getPages());
        System.out.println("总记录数"+pi.getTotal());
        System.out.println("连续显示的页码");
        int[] nums = pi.getNavigatepageNums();
        for (int i:nums) {
            System.out.println(" "+i);
        }

        //Blog

        List<Blog> list = pi.getList();
        for (Blog blog:list) {
//            System.out.println("Id: "+blog.getBlogid()+"=====>Name:"+blog.getUserid());
//            System.out.println("content:"+blog.getContent());
            System.out.println("Id: "+blog.getBlogid());
        }



    }


}

