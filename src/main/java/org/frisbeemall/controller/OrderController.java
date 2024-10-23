package org.frisbeemall.controller;

import org.frisbeemall.domain.Orders;
import org.frisbeemall.domain.Product;
import org.frisbeemall.service.OrdersService;
import org.frisbeemall.service.ProductService;
import org.frisbeemall.service.SellerService;
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
    @Autowired
    private SellerService sellerService;

    @GetMapping("/user/{userId}")
    public Result<List<Orders>> getOrderHistory(@PathVariable Integer userId) {

        return Result.success(orderService.findByUserId(userId));
    }

    @PostMapping("/create")
    public Result<String> addOrder(@RequestBody Orders order) {
        try {
            orderService.addOrder(order);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

        return Result.success("创建订单成功");
    }

    @GetMapping("/freeze/{id}")
    public Result<String> freeze(@PathVariable Integer id) {
        if(orderService.freezeOrder(id)>0)
         return Result.success("冻结订单成功");
        else{
            return Result.error("冻结订单失败");
        }

    }

    @GetMapping("/unfreeze/{id}")
    public Result<String> unfreeze(@PathVariable Integer id) {
        if(orderService.unfreezeOrder(id)>0)
            return Result.success("解冻订单成功");
        else{
            return Result.error("解冻订单失败");
        }
    }




    @PostMapping("/sell")
    public Result<String> sellOrder(@RequestBody Orders order) {
        try {
            productService.sellProduct(order.getProductId());
            orderService.sellOrder(order.getId());
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

        return Result.success("售出商品成功");
    }


}
