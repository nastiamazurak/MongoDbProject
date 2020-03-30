package com.edu.lnu.mongoDbPpoject.controller;

import com.edu.lnu.mongoDbPpoject.exception.IncorrectPasswordException;
import com.edu.lnu.mongoDbPpoject.exception.UserNotFoundByUsername;
import com.edu.lnu.mongoDbPpoject.exception.constant.ErrorMessage;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.security.AuthBody;
import com.edu.lnu.mongoDbPpoject.security.CookieProvider;
import com.edu.lnu.mongoDbPpoject.security.Helper;
import com.edu.lnu.mongoDbPpoject.security.JWTTokenProvider;
import com.edu.lnu.mongoDbPpoject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;
    private CookieProvider cookieProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider,
                                    UserServiceImpl userService, CookieProvider cookieProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.cookieProvider = cookieProvider;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public HttpStatus registration(@RequestBody User user) {
        userService.registerUser(user);
        return HttpStatus.OK;
    }

    @PostMapping("/sign-in")
    public void login(@RequestBody AuthBody body, HttpServletResponse response) throws IOException {
        String username = body.getNickName();
        try {
            if (userService.existsUserByUsername(username)) {

                if (userService.comparePasswordLogin(body, passwordEncoder)) {
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(username, body.getPassword()));
                }
                User user = userService.findUserByUserName(username);
                List<Cookie> list = Helper.createList(
                        cookieProvider.createCookie("JWT", jwtTokenProvider.generateAccessToken(user)),
                        cookieProvider.createCookie("jwt", jwtTokenProvider.generateRefreshToken())
                );

                for (Cookie cookie : list) {
                    response.addCookie(cookie);
                }
            }
        } catch (UserNotFoundByUsername ex) {
            response.sendError(404, "User not found with username: " + username);
        } catch (IncorrectPasswordException ex) {
            response.sendError(400, ErrorMessage.INVALID_USERNAME_OR_PASSWORD);
        }
    }

    @GetMapping("/sign-out")
    public void getHome(HttpServletRequest request, HttpServletResponse response) {
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(cookieProvider.deleteCookie("JWT"));
        cookieList.add(cookieProvider.deleteCookie("jwt"));

        for(Cookie cookie : cookieList){
            response.addCookie(cookie);
        }
    }
}
