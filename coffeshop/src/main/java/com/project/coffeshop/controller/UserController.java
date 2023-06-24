package com.project.coffeshop.controller;

import com.project.coffeshop.pojo.request.LoginPojo;
import com.project.coffeshop.pojo.request.UserPojo;
import com.project.coffeshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController extends BaseController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserPojo userPojo){
        return ResponseEntity.ok(successResponse("Successfully sign up", userService.signUp(userPojo)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginPojo loginPojo){
        return ResponseEntity.ok(successResponse("Successfully sign in", userService.signIn(loginPojo)));
    }

    @PostMapping("sign-out")
    public ResponseEntity<?> signOut(@RequestParam("username") String username, HttpServletRequest request) {
        String token = getAccessToken(request);
        return  ResponseEntity.ok(successResponse("successfully logout", userService.signOut(username, token)));
    }

    @GetMapping("refresh")
    public ResponseEntity<?> getToken(@RequestParam("refreshToken") String refreshToken){
        return ResponseEntity.ok(successResponse("Successfully refresh", userService.getToken(refreshToken)));
    }

}
