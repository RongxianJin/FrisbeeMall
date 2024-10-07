package org.frisbeemall.service;

import org.frisbeemall.dao.SellerDao;
import org.frisbeemall.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDao;

    public int updatepassword(long id, String password) {
        Seller seller=sellerDao.selectById(id);
        seller.setPassword(password);
        return sellerDao.updateById(seller);

    }


}
