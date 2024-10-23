package org.frisbeemall.service;

import org.frisbeemall.dao.OrdersDao;
import org.frisbeemall.dao.ProductDao;
import org.frisbeemall.domain.Orders;
import org.frisbeemall.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;


    public List<Product> getCurrentProduct() {


        return productDao.selectCurrentProduct();
    }

    public Product getProductById(int id) {
        return productDao.selectById(id);
    }

    public List<Product> getAllProducts() {
        return productDao.selectList(null);
    }

    @Transactional
    public void addProduct(String name, String description, String price, String imageUrl) {
        int status=0;
       productDao.insertProduct(name, description, imageUrl, price, status);

    }



    @Transactional
    public int sellProduct(Integer productId) {
        Product product = productDao.selectById(productId);
        product.setStock(product.getStock() - 1);
        if(product.getStock()==0) product.setStatus(1);
        return productDao.updateById(product);


    }



}
