package org.frisbeemall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.ProductImages;

import java.util.List;

@Mapper
public interface ProductImagesDao extends BaseMapper<ProductImages> {


    @Select("select image_url from product_images where product_id = #{id} ")
    public List<String> selectImageUrlByProductId(Integer id);


}
