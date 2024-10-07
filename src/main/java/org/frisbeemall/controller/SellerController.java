package org.frisbeemall.controller;

import org.frisbeemall.domain.Seller;
import org.frisbeemall.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    @PostMapping("/update/{id}/{password}")
    public ResponseEntity<Result<String>> updatePassword(@PathVariable("id") Long id, @PathVariable("password") String password) {
        sellerService.updatepassword(id, password);
        return ResponseEntity.ok(Result.success("密码更新成功"));
    }
}

