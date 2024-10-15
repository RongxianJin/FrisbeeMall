package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select  * from user where product_id = #{id}")
    public List<User> getUserById(Long productId);

}
