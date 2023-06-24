package com.project.coffeshop.pojo.request;

import lombok.Data;

@Data
public class UserPojo {

    private String username;

    private String password;

    private String email;

    private Long cafeId;

    private String name;

    private String role;
}
