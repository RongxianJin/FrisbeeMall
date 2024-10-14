package org.frisbeemall.service;

import org.frisbeemall.controller.Result;
import org.frisbeemall.dao.SellerDao;
import org.frisbeemall.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDao;

    public int updatepassword(Long id, String password) {
        Seller seller=sellerDao.selectById(id);
        seller.setPassword(password);
        return sellerDao.updateById(seller);

    }




        public int login(String username, String password) {
            Seller seller = sellerDao.findByUsername(username);

            if (seller == null ) {

                return 1;
            }

            if (!password.equals(seller.getPassword())) {

                return 2;
            }
            return 0;
        }

    }

