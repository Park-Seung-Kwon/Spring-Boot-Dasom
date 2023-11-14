package com.example.dasom.controller;


import com.example.dasom.domain.dto.DonateWriteDto;
import com.example.dasom.domain.vo.DonateWriteVo;
import com.example.dasom.service.DonateWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/donateWrite/*")
@RequiredArgsConstructor
@Slf4j
public class DonateWriteController {

    private final DonateWriteService donateWriteService;

    //    관리자페이지 후원 글 작성 페이지 이동
    @GetMapping("/write")
    public String showDonateWrite(HttpServletRequest req){
        Long adminNumber = (Long)req.getSession().getAttribute("adminNumber");

        return adminNumber == null ? "admin/adLogin":"admin/adWrite/adWriteDonation";
    }

//    후원 글 작성
    @PostMapping("/write")
    public RedirectView donateWrite(DonateWriteDto donateWriteDto, HttpServletRequest req, RedirectAttributes redirectAttributes,
                                    @RequestParam("donateFile")MultipartFile file){
        Long adminNumber = (Long)req.getSession().getAttribute("adminNumber");

        donateWriteDto.setAdminNumber(adminNumber);
        donateWriteService.registerAndFileProc(donateWriteDto, file);

        Long donateWriteNumber = donateWriteDto.getDonateWriteNumber();

        redirectAttributes.addFlashAttribute("donateWriteNumber", donateWriteNumber);

        return new RedirectView("/admin/adDonation");
    }

//    관리자 후원 글 수정 페이지 이동
    @GetMapping("/modify")
    public String showModify(@RequestParam("donateWriteNumber") Long donateWriteNumber, Model model){
        DonateWriteVo donateWriteVo =donateWriteService.find(donateWriteNumber);
        model.addAttribute("donate", donateWriteVo);
        return "admin/adModify/adModifyDonation";
    }

//    후원 글 삭제
    @GetMapping("/remove")
    public ResponseEntity<String> remove(Long donateWriteNumber){
        donateWriteService.remove(donateWriteNumber);
        return ResponseEntity.ok("삭제 성공!");
    }

//    후원 글 수정
    @PostMapping("/modify")
    public RedirectView modify(DonateWriteDto donateWriteDto, RedirectAttributes redirectAttributes,
                               @RequestParam("donateWriteFile") MultipartFile file){

        log.info("============================= {}", file.toString());
        donateWriteService.modify(donateWriteDto, file);

        redirectAttributes.addAttribute("donateWriteNumber", donateWriteDto.getDonateWriteNumber());

        return  new RedirectView("/admin/adDonation");
    }

//    후원 글 모집 완료
    @GetMapping("/recruit")
    public ResponseEntity<String> recruit(Long donateWriteNumber){
        donateWriteService.recruit(donateWriteNumber);
        return ResponseEntity.ok("모집완료 성공!");
    }
}
