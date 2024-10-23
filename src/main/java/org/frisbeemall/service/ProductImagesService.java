package org.frisbeemall.service;

import org.frisbeemall.dao.ProductImagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImagesService {

    @Autowired
    private ProductImagesDao productImagesDao;

    public List<String> getProductImagesByProductId(int productId) {
        List<String> images;
        images = productImagesDao.selectImageUrlByProductId(productId);
        return images;
    }
}
