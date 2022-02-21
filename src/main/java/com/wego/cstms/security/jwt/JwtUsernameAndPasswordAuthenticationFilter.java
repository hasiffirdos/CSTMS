package com.wego.cstms.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    static final class ResponseMessage {
        String message;
    }
    public static String getUserType(String auth){
        String type = "";
        if (auth.contains("ROLE_")){
            type = auth.replace("ROLE_","");
        }
        return type;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()

            );

            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        String token = Jwts.builder().setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(secretKey)
                .compact();
        String userType = "";
        for (GrantedAuthority auth : authResult.getAuthorities()){
            if (auth.getAuthority().contains("ROLE_")){
                userType = auth.getAuthority().replace("ROLE_","");
            }
        }
        response.setHeader(jwtConfig.getAuthorizationHeader(),jwtConfig.getTokenPrefix()+token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization, UserType, UserName");
        response.setHeader("UserType",userType);
        response.setHeader("UserName",authResult.getName());
        response.setHeader("Content-Type", "application/json");
        ResponseMessage resMessage = new ResponseMessage();
        resMessage.message = "Login Successfully";
        String resp = new Gson().toJson(resMessage);
        response.getWriter().write(resp);
        response.getWriter().flush();

//
//        chain.doFilter(request,response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json");
        ResponseMessage resMessage = new ResponseMessage();
        resMessage.message = "Username or Password is not valid...!";
        String resp = new Gson().toJson(resMessage);
        response.getWriter().write(resp);
        response.getWriter().flush();
    }
}


