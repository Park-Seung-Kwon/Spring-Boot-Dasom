package com.example.dasom.controller;

import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.PageVo;
import com.example.dasom.domain.vo.SearchVo;
import com.example.dasom.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final DonateWriteService donateWriteService;
    private final CsWriteService csWriteService;
    private final AdUserService adUserService;
    private final AdDonateService adDonateService;

//    관리자 로그인 페이지 이동
    @GetMapping("/adLogin")
    public String showAdminLogin(){
        return "admin/adLogin/adLogin";
    }

//    관리자 로그인
    @PostMapping("/login")
    public RedirectView login(String adminId, String adminPassword, HttpServletRequest req){
        Long adminNumber = adminService.selectAdminNumber(adminId, adminPassword);
        req.getSession().setAttribute("adminNumber", adminNumber);

        return new RedirectView("/admin/adUser");
    }

//    관리자페이지 후원글 관리 이동
    @GetMapping("/adDonation")
    public String showAdDonation(Criteria criteria, Model model,
                                 @ModelAttribute("searchVo") SearchVo searchVo){

        log.info("======================== keyword : " + searchVo.getKeyword());
        model.addAttribute("searchKeyword", searchVo.getKeyword());
        model.addAttribute("searchCate", searchVo.getCate());
        model.addAttribute("donateWriteList", donateWriteService.findAll(criteria, searchVo));
        model.addAttribute("pageInfo", new PageVo(donateWriteService.getTotal(searchVo), criteria));
        return "admin/adDonation/adDonation";
    }

//    관리자페이지 회원관리 이동
    @GetMapping("/adUser")
    public String showAdUser(Criteria criteria, Model model,
                             @ModelAttribute("searchVo") SearchVo searchVo){

        log.info("======================== keyword : " + searchVo.getKeyword());
        model.addAttribute("searchKeyword", searchVo.getKeyword());
        model.addAttribute("searchCate", searchVo.getCate());
        model.addAttribute("userList", adUserService.findAll(criteria,searchVo));
        model.addAttribute("pageInfo", new PageVo(adUserService.getTotal(searchVo),criteria));

        return "admin/adUser/adUser";
    }

//    관리자페이지 후원금리스트 이동
    @GetMapping("/adDonationList")
    public String showAdDonationList(Criteria criteria, Model model,
                                     @ModelAttribute("searchVo") SearchVo searchVo) {

        log.info("======================== keyword : " + searchVo.getKeyword());
        model.addAttribute("searchKeyword", searchVo.getKeyword());
        model.addAttribute("searchCate", searchVo.getCate());
        model.addAttribute("donateList", adDonateService.findAll(criteria, searchVo));
        model.addAttribute("pageInfo", new PageVo(adDonateService.getTotal(searchVo), criteria));
        return "admin/adDonationList/adDonationList";
    }

//    관리자페이지 봉사글 관리 페이지 이동
    @GetMapping("/adCs")
    public String showAdCs(Criteria criteria, Model model,
                           @ModelAttribute("searchVo") SearchVo searchVo){

        log.info("======================== keyword : " + searchVo.getKeyword());
        model.addAttribute("searchKeyword", searchVo.getKeyword());
        model.addAttribute("searchCate", searchVo.getCate());
        model.addAttribute("csWriteList", csWriteService.findAll(criteria, searchVo));
        model.addAttribute("pageInfo", new PageVo(csWriteService.getTotal(searchVo), criteria));

        return "admin/adCs/adCs";
    }

//    관리자 로그아웃
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req){
        req.getSession().invalidate();
        return new RedirectView("/admin/adLogin");
    }







}
