package com.project.coffeshop.config;

import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.exception.UnAuthorizeException;
import com.project.coffeshop.service.UserTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    private final UserTokenService userTokenService;


    public static String AUTHORIZATION_HEADER = "authorization";

    public static String BEARER_TOKEN = "Bearer";

    private Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    public RequestInterceptor(UserTokenService userTokenService) {
        this.userTokenService = userTokenService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if(token == null) throw new UnAuthorizeException("Unauthorized");
        if(!token.startsWith(BEARER_TOKEN)) throw new UnAuthorizeException("Token malformed");
        token = token.substring(7);
        UserEntity user = userTokenService.getUser(token);
        logger.info(user.getUsername() + " called api");
        if(userTokenService.isTokenExpired(token)) throw new UnAuthorizeException("token expired generate new token with refresh token or login ");

        return true;
    }
}
