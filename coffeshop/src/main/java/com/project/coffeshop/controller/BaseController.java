package com.project.coffeshop.controller;

import com.project.coffeshop.enums.ResponseStatusEnum;
import com.project.coffeshop.pojo.response.GlobalApiResponse;
import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {


    public String getAccessToken(HttpServletRequest request){
        return null;
    }

    public GlobalApiResponse successResponse(String message, Object data){
        return GlobalApiResponse.builder()
                .status(ResponseStatusEnum.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }
}
