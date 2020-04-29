package com.four.test;

import com.four.dao.BlogMapper;
import com.four.dao.UserMapper;
import com.four.domain.Blog;
import com.four.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ContextConfiguration spring测试
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class mybatisTest {




        @Autowired
        UserMapper userMapper;

        @Autowired
        BlogMapper blogMapper;

        @Autowired
        SqlSession sqlSession;


        @Test
        public void testCRUD(){
            //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        System.out.println(departmentMapper);
//
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
//
//
//        employeeMapper.insertSelective(new Employee(null,"jerry","M","123@163.com",1));
//
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            for (int i = 0; i < 100; i++) {
                String username = UUID.randomUUID().toString().substring(0,5);
                mapper.insertSelective(new User(null,username,5,"123456"));

            }





        }


    @Test
    public void selectByUserId()
    {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
       // mapper.insertSelective(new Blog(null,41,new Date(System.currentTimeMillis()),"今天要好好加油啊！！不要泄气！！"));
        List<Blog> blogs = mapper.selectAll();
        for(Blog blog:blogs){
            System.out.println(blog.toString());
        }
    }


    @Test
    public void insertBlogs()
    {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.insertSelective(new Blog(null,41,new Date(System.currentTimeMillis()),"今天天气真好"));
        for (int i = 0; i < 100; i++) {
            String content = UUID.randomUUID().toString().substring(0,5)+"鹿晗冲啊！！";
            mapper.insertSelective(new Blog(null,55,new Date(System.currentTimeMillis()),content));

        }
    }




}
