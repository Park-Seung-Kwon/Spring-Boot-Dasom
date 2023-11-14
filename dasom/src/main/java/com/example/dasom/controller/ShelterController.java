package com.example.dasom.controller;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.service.ShelterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shelter/*")
@RequiredArgsConstructor
@Slf4j
public class ShelterController {

    private final ShelterService shelterService;

    @GetMapping("/shelter")
    public String showShelter(){
        return "include/mapApi";
    }

    @GetMapping("/shelterLogin")
    public String showShelterLogin(HttpServletRequest req, Model model){

        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

        UserDto userDto = shelterService.find(userNumber);

        String userAddr = userDto.getUserAddr(); // userDto.getUserAddr()에서 주소 문자열 가져오기
        String[] addrParts = userAddr.split(" "); // 띄어쓰기로 문자열 분할

        String addr1 = "";
        String addr2 = "";
        if (addrParts.length > 0) {
            addr1 = addrParts[0];
            model.addAttribute("addr1",addr1);
            addr2 = addrParts[1];
            model.addAttribute("addr2", addr2);

        }

        String addrExtra = userDto.getUserAddExtra();
        if (addrExtra.length() >= 2) {
            addrExtra = addrExtra.substring(2, addrExtra.length() - 1);
        } else {
            addrExtra = "";
        }
        model.addAttribute("addr3", addrExtra);
        model.addAttribute("userInfo", userDto);
        return "include/mapApiLogin";
    }
}
