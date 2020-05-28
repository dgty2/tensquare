package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

/** @Author lpt @Date 2019/6/12 15:55 @Version 1.0 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

  @Autowired private JwtUtil jwtUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
   // System.out.println("经过了拦截器");
    String header = request.getHeader("Authorization");
    if (StringUtils.isNotEmpty(header)) {
      // 如果有包含有Authorization头信息，就对其进行解析
      if (header.startsWith("Bearer ")) {
        String token = header.substring(7);
        // 对令牌进行验证
        try {
          Claims claims = jwtUtil.parseJWT(token);
          String roles = (String) claims.get("roles");
          if (roles != null && roles.equals("admin")) {
            request.setAttribute("claims_admin", token);
          }
          if (roles != null && roles.equals("user")) {
            request.setAttribute("claims_user", token);
          }
        } catch (Exception e) {
          throw new RuntimeException("令牌不正确");
        }
      }
    }
    return true;
  }
}
