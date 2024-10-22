package org.frisbeemall.controller;

import org.frisbeemall.domain.Orders;
import org.frisbeemall.domain.Product;
import org.frisbeemall.service.OrdersService;
import org.frisbeemall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/user/{userId}")
    public List<Orders> getOrderHistory(@PathVariable Integer userId) {
        return orderService.findByUserId(userId);
    }

    @PostMapping("/buy")
    public Result<String> addOrder(@RequestBody Orders order) {
        Product product =productService.getProductById(order.getProductId());
        product.setStock(product.getStock()-order.getTotalAmount());
        productService.sellProduct(product);
        orderService.addOrder(order);

        return Result.success("创建订单成功");
    }


}
