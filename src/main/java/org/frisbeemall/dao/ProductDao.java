package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.Product;

import java.util.List;


@Mapper
public interface ProductDao extends BaseMapper<Product> {

    @Select("SELECT * FROM product WHERE status = 0")
    List<Product> selectCurrentProduct();

    @Insert("INSERT INTO product (name, description, image_url, price, status) VALUES (#{name}, #{description}, #{imageUrl}, #{price}, #{status})")
    int insertProduct(String name, String description, String imageUrl, String price, int status);
}
