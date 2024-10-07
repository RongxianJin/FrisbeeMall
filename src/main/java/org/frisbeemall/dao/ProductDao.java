package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.Product;


@Mapper
public interface ProductDao extends BaseMapper<Product> {

    @Select("SELECT * FROM products WHERE status = 0")
    Product selectCurrentProduct();
}
