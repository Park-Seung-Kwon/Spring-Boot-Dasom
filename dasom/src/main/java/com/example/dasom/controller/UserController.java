package com.example.dasom.controller;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDto userDto;


    @GetMapping("/join")
    public String showJoinPage(){
        return "user/join/join";
    }

    @PostMapping("/joinPage")
    public RedirectView join(UserDto userDto){
        userService.register(userDto);
        return new RedirectView("/user/login");
    }


    @GetMapping("/login")
    public String showLoginPage(){
        return "user/login/login";
    }

    @PostMapping("/loginPage")
    public RedirectView login(String userId, String userPassword, HttpServletRequest req){
        UserDto userDto = userService.find(userId, userPassword);
        req.getSession().setAttribute("userNumber", userDto.getUserNumber());

        return new RedirectView("/main/mainPage");
    }


    @GetMapping("/termOfUse")
    public String termOfUse(){
        return "include/termOfUse";
    }


    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req){
        req.getSession().invalidate();
        return new RedirectView("/main/mainPage");
    }

}
