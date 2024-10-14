package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.Seller;

@Mapper
public interface SellerDao extends BaseMapper<Seller> {

@Select("select * from seller where username = #{username}")
    Seller findByUsername(String username);
}
