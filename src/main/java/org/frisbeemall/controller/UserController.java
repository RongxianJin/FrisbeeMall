package org.frisbeemall.controller;

import org.frisbeemall.domain.User;
import org.frisbeemall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Result<String>> register( @RequestParam String name, @RequestParam String password ,@RequestParam String phone ,@RequestParam String location    ) {
        userService.addUser(name, password, phone, location);
        return ResponseEntity.ok(Result.success("提交成功"));
    }


    @PutMapping("/choose/{id}")
    public ResponseEntity<Result<String>> choose(@PathVariable Integer id) {
        userService.chooseUser(id);
        return ResponseEntity.ok(Result.success("选择用户成功"));
    }



}
