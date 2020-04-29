package com.four.dao;

import com.four.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);




    int deleteByPrimaryKey(Integer userid);

    User selectByPrimaryKey(Integer userid);

    User selectByUserName(@Param("username") String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}