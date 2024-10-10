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

    @PostMapping("/submit")
    public ResponseEntity<Result<String>> submit(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(Result.success("提交成功"));
    }

    @GetMapping("/show")
    public ResponseEntity<Result<List<User>>> show() {
        List<User> users=userService.getUsers();
        return ResponseEntity.ok((Result.success(users)));

    }
    @PutMapping("/choose/{id}")
    public ResponseEntity<Result<String>> choose(@PathVariable Integer id) {
        userService.chooseUser(id);
        return ResponseEntity.ok(Result.success("选择用户成功"));
    }



}
