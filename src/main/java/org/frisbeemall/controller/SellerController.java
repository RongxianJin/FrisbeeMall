package org.frisbeemall.controller;

import org.frisbeemall.domain.Seller;
import org.frisbeemall.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<Result<String>> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 调用服务层的登录验证方法
        int seller = sellerService.login(username, password);

        if (seller == 0) {
            return ResponseEntity.ok(Result.success("登录成功"));
        } else if (seller == 1) {
            return ResponseEntity.status(401).body(Result.error("用户名错误"));
        }
        else {
            return ResponseEntity.status(401).body(Result.error("密码错误"));
        }
    }
}

