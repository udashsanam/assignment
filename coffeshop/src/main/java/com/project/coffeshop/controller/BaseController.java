package com.project.coffeshop.controller;

import com.project.coffeshop.enums.ResponseStatusEnum;
import com.project.coffeshop.pojo.response.GlobalApiResponse;
import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {


    public static String AUTHORIZATION_HEADER = "authorization";

    public static String BEARER_TOKEN = "Bearer";


    public String getAccessToken(HttpServletRequest request){

        String accessToken = request.getHeader(AUTHORIZATION_HEADER);
        if(!accessToken.startsWith(BEARER_TOKEN))  throw new RuntimeException("Token malformed");
        return accessToken.substring(7);
    }

    public GlobalApiResponse successResponse(String message, Object data){
        return GlobalApiResponse.builder()
                .status(ResponseStatusEnum.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }




}
