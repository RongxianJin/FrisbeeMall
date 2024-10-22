package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select  * from user where product_id = #{id}")
    List<User> getUserById(Integer productId);

    @Insert("INSERT INTO  user(username, password, phone, location) values (#{username}, #{password}, #{phone}, #{location})")
    int insertUser(String username,String password,String phone,String location);

}
