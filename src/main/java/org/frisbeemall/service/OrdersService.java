package org.frisbeemall.service;


import org.frisbeemall.dao.OrdersDao;
import org.frisbeemall.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
