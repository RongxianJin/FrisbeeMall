package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.frisbeemall.domain.Categories;

@Mapper
public interface CategoryDao extends BaseMapper<Categories> {
}
