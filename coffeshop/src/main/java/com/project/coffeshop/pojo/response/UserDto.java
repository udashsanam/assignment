package com.project.coffeshop.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String username;

    private String cafeName;

    private String email;

    public UserDto(String username, String email, String cafeName) {
        this.username = username;
        this.cafeName = cafeName;
        this.email = email;
    }
}
