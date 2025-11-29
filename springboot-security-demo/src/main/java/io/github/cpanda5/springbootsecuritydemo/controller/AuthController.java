package io.github.cpanda5.springbootsecuritydemo.controller;

import io.github.cpanda5.springbootsecuritydemo.dto.LoginRequest;
import io.github.cpanda5.springbootsecuritydemo.result.Result;
import io.github.cpanda5.springbootsecuritydemo.result.ResultCodeEnum;
import io.github.cpanda5.springbootsecuritydemo.util.JwtUtil;
import io.github.cpanda5.springbootsecuritydemo.vo.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        if ("test".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(1L, request.getUsername());
            return Result.ok(new LoginResponse(token));
        } else {
            return Result.fail(ResultCodeEnum.ADMIN_ACCOUNT_ERROR.getCode(),
                    ResultCodeEnum.ADMIN_ACCOUNT_ERROR.getMessage());
        }
    }

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.ok("Hello World");
    }
}
