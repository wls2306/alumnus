package com.bcu.alumnus;

import com.bcu.alumnus.exception.InvalidTokenException;
import com.bcu.alumnus.exception.PermissionDeniedExpectation;
import com.bcu.alumnus.exception.TokenNotExistsExpectation;
import com.bcu.alumnus.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
* @Author: Wls
* @Date: 17:37 2020/4/5
* @Description: 权限验证拦截器
*/
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(UseToken.class)) {
            UseToken useToken=method.getAnnotation(UseToken.class);
            String token=request.getHeader("token");
            if (token==null) {
                throw new TokenNotExistsExpectation();
            }
            Claims claims;
            try {
                 claims = JwtUtil.resolveToken(token);
            }catch (Exception e) {
                throw new InvalidTokenException();
            }

            if (claims==null||Integer.parseInt((String)claims.get("userType"))<useToken.level()) {
                throw new PermissionDeniedExpectation();
            }
        }
        return true;


    }
}

