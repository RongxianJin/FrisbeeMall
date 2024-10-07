package org.frisbeemall.service;

import org.frisbeemall.dao.ProductDao;
import org.frisbeemall.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Product getCurrentProduct() {
        return productDao.selectCurrentProduct(); // 假设只有一个商品，ID 为 1
    }

    @Transactional
    public int freezeProduct(long id) {
        Product product = productDao.selectById(id);
        product.setStatus(1);
        return productDao.updateById(product);
    }

    @Transactional
    public int  unfreezeProduct(long id) {
        Product product = productDao.selectById(id);
        product.setStatus(0);
        return productDao.updateById(product);
    }

    public List<Product> getAllProducts() {
        return productDao.selectList(null);
    }

    @Transactional
    public int  addProduct(Product product) {
        return  productDao.insert(product);
    }

    @Transactional
    public int sellProduct(long id) {
        Product product=productDao.selectById(id);
        product.setStatus(3);
        return productDao.updateById(product);

    }



}
