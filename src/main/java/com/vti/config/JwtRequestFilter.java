package com.vti.config;

import com.vti.modal.dto.LoginDto;
import com.vti.service.JWTTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization"; // key giống trong postman để truyền bearer


    @Autowired
    private JWTTokenUtils jwtTokenUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        // Lấy token từ api (request)
        String token = httpServletRequest.getHeader(AUTHORIZATION); // get header truyền key là authorization để lấy value là dãy token
        String request = httpServletRequest.getRequestURI(); // xem api nào ko cần check token thì truyền vào


        if (StringUtils.containsAnyIgnoreCase(request, "/api/v1/auth/login-jwt")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/account/create-account")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/auth/dangky")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/song/get-all-song")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/singer/search-singer")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/playlist/get-all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/song/search")
                || StringUtils.containsAnyIgnoreCase(request, "/swagger-ui")
                || StringUtils.containsAnyIgnoreCase(request, "/swagger-resources")
                || StringUtils.containsAnyIgnoreCase(request, "/v3/api-docs")) {
            // Những api public ko cần check token -> doFilter : là được đi tiếp luôn
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {

                // kiểm tra và Giải mã token -> lấy thông tin user -> authen
                LoginDto loginDto = jwtTokenUtils.checkToken(token,httpServletResponse,httpServletRequest);
                if (loginDto != null){
                    // Lấy danh sách quyền của user
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(loginDto.getRole());
                    // Tạo đối tượng để Authen vào hệ thống
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);// set đối tượng vào hệ thống
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }


        }
    }
}

