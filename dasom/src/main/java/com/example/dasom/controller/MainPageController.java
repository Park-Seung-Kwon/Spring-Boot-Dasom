package com.example.dasom.controller;

import com.example.dasom.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/mainPage")
    public String main(Model model){

        int amount = mainPageService.findAmount();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedAmount = decimalFormat.format(amount);
        model.addAttribute("amount", formattedAmount);

        model.addAttribute("donateList", mainPageService.findDonateAll());
        model.addAttribute("csList", mainPageService.findCsAll());
        return "mainPage";

    }


}
