package com.project.coffeshop.pojo.request;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginPojo {

    private String username;

    private String password;
}
