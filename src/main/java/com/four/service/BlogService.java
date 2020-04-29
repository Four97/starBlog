package com.four.service;

import com.four.dao.BlogMapper;
import com.four.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogService {

    @Autowired
    BlogMapper blogMapper;

    public List<Blog> selectAll(){
        return blogMapper.selectAll();
    }


}