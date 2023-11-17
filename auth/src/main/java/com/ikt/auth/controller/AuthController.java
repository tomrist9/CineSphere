package com.ikt.auth.controller;

import com.ikt.auth.BaseExceptionHandler;
import com.ikt.auth.dto.request.UserCreateRequestDto;
import com.ikt.auth.dto.request.UserLoginRequestDto;
import com.ikt.auth.dto.response.UserResponseDto;
import com.ikt.auth.exception.NotFoundException;
import com.ikt.auth.exception.UserAlreadyExistException;
import com.ikt.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseExceptionHandler {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistException {
        Pair<UserResponseDto, String> userInfo = authService.create(userCreateRequestDto);
        return new ResponseEntity<>(userInfo.getFirst(),getLoginHeader(userInfo.getSecond()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) throws NotFoundException {
        Pair<UserResponseDto, String> userInfo = authService.login(userLoginRequestDto);
        return new ResponseEntity<>(userInfo.getFirst(),getLoginHeader(userInfo.getSecond()), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public void delete(HttpServletRequest httpServletRequest) throws NotFoundException {
        String email = httpServletRequest.getHeader("email");
        authService.deleteUser(email);
    }



    private HttpHeaders getLoginHeader(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Expose-Headers", "authorization");
        responseHeaders.add("Authorization", token);
        return responseHeaders;
    }

}
