package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController extends BaseController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    private ResponseEntity<?> signUp(@RequestBody UserPojo userPojo){
        return ResponseEntity.ok(successResponse("Successfully sign up", userService.signUp(userPojo)));
    }
}
