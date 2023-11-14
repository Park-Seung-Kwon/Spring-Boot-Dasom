package com.example.dasom.controller;

import com.example.dasom.service.SmsService;
import com.example.dasom.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/users/*")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    private final UserService userService;
    private final SmsService smsService;

    @GetMapping(value="/checkId")
    public int checkId(String userId) throws Exception {

        int count = 0;
        if(userId != null) count = userService.checkId(userId);

        return count;
    }

    @PostMapping("/send")
    public Mono<Map> sendMsg(@RequestBody Map<String, String> body, HttpServletRequest req){
        String phoneNumber = body.get("phoneNumber");
        Map<String, Object> result = smsService.sendMessage(phoneNumber);
        String authNumber = (String) result.get("authNumber");
        Mono<Map> mono = (Mono<Map>) result.get("mono");
        req.getSession().setAttribute("authNumber", authNumber);
        return mono;
    }

    @PostMapping("/check")
    public boolean checkNumber(@RequestBody Map<String, String> map, HttpServletRequest req){
        log.info(map.get("checkNumber"));
        String authNumber = (String) req.getSession().getAttribute("authNumber");
        return authNumber.equals(map.get("checkNumber"));
    }


}
