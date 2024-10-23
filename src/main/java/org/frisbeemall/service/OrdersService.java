package org.frisbeemall.service;


import org.frisbeemall.dao.OrdersDao;
import org.frisbeemall.domain.Orders;
import org.frisbeemall.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    public List<Orders> findByUserId(Integer id) {
        return ordersDao.findByUserId(id);
    }





    public int addOrder(Orders orders) {
        return ordersDao.insertOrder(orders.getUserId(),orders.getOrderDate(),orders.getTotalAmount(),orders.getStatus());
    }

    @Transactional
    public int freezeOrder(Integer id) {
       Orders orders = ordersDao.selectById(id);
       orders.setStatus(1);
       return ordersDao.updateById(orders);
    }

    @Transactional
    public int  unfreezeOrder(Integer id) {
        Orders orders = ordersDao.selectById(id);
        orders.setStatus(0);
        return ordersDao.updateById(orders);
    }

    @Transactional
    public int  sellOrder(Integer id) {
        Orders orders = ordersDao.selectById(id);
        orders.setStatus(2);
        return ordersDao.updateById(orders);
    }


}
