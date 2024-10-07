package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.frisbeemall.domain.User;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao extends BaseMapper<User> {


}
