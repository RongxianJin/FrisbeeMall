package org.frisbeemall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.frisbeemall.domain.Orders;
import org.springframework.core.annotation.Order;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

    @Select("select * from orders where user_id = #{id}")
    List<Orders> findByUserId(Integer id);

    @Insert("INSERT INTO orders (user_id, order_date, total_amount, status) VALUES (#{userId}, #{orderDate}, #{totalAmount}, #{status})")
    int insertOrder(Integer userId, Date orderDate, Integer totalAmount, Integer status);
}
