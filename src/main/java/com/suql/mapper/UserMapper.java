package com.suql.mapper;

import com.suql.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

//    @Insert("insert into suql_user(user_name, user_age, user_sex) values(#{userName}, #{age}, #{sex})")
    int insert(UserInfo userInfo);
}
