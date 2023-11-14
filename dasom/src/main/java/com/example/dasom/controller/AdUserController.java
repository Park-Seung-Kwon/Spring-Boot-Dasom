package com.example.dasom.controller;

import com.example.dasom.domain.dto.DonateDto;
import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.service.AdUserService;
import com.example.dasom.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/adUser/*")
@RequiredArgsConstructor
@Slf4j
public class AdUserController {

    private final AdUserService adUserService;
    private final MyPageService myPageService;

//    회원정보 상세보기

    @GetMapping("/detail")
    public String showUserDetail(@RequestParam("userNumber") Long userNumber,
                                 Model model){
        UserDto userDto = adUserService.find(userNumber);
        model.addAttribute("user", userDto);
        model.addAttribute("donation", myPageService.donateUserSelectAll(userNumber));
        model.addAttribute("cs", myPageService.csUserSelectAll(userNumber));
        return "admin/adUserDetail/adUserDetail";
    }

//    회원 탈퇴
    @GetMapping("/remove")
    public ResponseEntity<String> remove(Long userNumber) {
        adUserService.remove(userNumber);
        return ResponseEntity.ok("삭제 성공!");
    }

}
