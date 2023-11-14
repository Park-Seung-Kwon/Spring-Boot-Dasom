package com.example.dasom.controller;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.CsWriteVo;
import com.example.dasom.service.CsWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/csWrite/*")
@RequiredArgsConstructor
@Slf4j
public class CsWriteController {

    private final CsWriteService csWriteService;

//    관리자 봉사 모집 글 작성 페이지 이동
    @GetMapping("/write")
    public String showCsWrite(HttpServletRequest req){
        Long adminNumber = (Long)req.getSession().getAttribute("adminNumber");

        return adminNumber == null ? "admin/adLogin":"admin/adWrite/adWriteCs";
    }

//    봉사 글 작성
    @PostMapping("/write")
    public RedirectView csWrite(CsWriteDto csWriteDto, HttpServletRequest req, RedirectAttributes redirectAttributes,
                                @RequestParam("csWriteFile")MultipartFile file){
        Long adminNumber = (Long)req.getSession().getAttribute("adminNumber");

        csWriteDto.setAdminNumber(adminNumber);
        csWriteService.registerAndFileProc(csWriteDto, file);

        Long csWriteNumber = csWriteDto.getCsWriteNumber();

        redirectAttributes.addFlashAttribute("csWriteNumber", csWriteNumber);

        return new RedirectView("/admin/adCs");
    }

//    관리자 봉사 글 수정 페이지 이동
    @GetMapping("/modify")
    public String showModify(@RequestParam("csWriteNumber") Long csWriteNumber, Model model){

        CsWriteVo csWriteVo = csWriteService.find(csWriteNumber);
        model.addAttribute("cs", csWriteVo);
        return "admin/adModify/adModifyCs";
    }

//    봉사 글 삭제
    @GetMapping("/remove")
    public ResponseEntity<String> remove(Long csWriteNumber){
        csWriteService.remove(csWriteNumber);
        return ResponseEntity.ok("삭제 성공!");
    }

//    봉사 글 수정
    @PostMapping("/modify")
    public RedirectView modify(CsWriteDto csWriteDto, RedirectAttributes redirectAttributes,
                               @RequestParam("csWriteFile") MultipartFile file){

        log.info("============================= {}", file.toString());
        csWriteService.modify(csWriteDto, file);

        redirectAttributes.addAttribute("csWriteNumber", csWriteDto.getCsWriteNumber());

        return new RedirectView("/admin/adCs");
    }

//    봉사 글 모집 완료
    @GetMapping("/recruit")
    public ResponseEntity<String> recruit(Long csWriteNumber){
        csWriteService.recruit(csWriteNumber);
        return ResponseEntity.ok("모집완료 성공!");
    }
}
