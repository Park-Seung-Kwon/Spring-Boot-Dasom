package com.example.dasom.controller.cs;

import com.example.dasom.domain.vo.CsDetailVo;
import com.example.dasom.service.CsListService;
import com.example.dasom.service.DonateListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CsListController {
    private final CsListService csListService;
    private final DonateListService donateListService;
//    봉사 게시판 리스트출력
    @GetMapping("/list")
    public String showList(){
        return  "cs/csList/csList";
    }

// 오류잡기 추가하기!!
//    봉사 게시글 상세보기
    @GetMapping("/detail")
    public String showDetail(@RequestParam("csNum")Long csNum , Model model){
        CsDetailVo csDetailVo = csListService.showDetail(csNum);

        model.addAttribute("csInfo", csDetailVo);
        model.addAttribute("csNum",csNum);
        return "cs/csDetail/csDetail";
    }

    //    봉사신청 완료페이지
    @GetMapping("/csComplete")
    public String csComplete(HttpServletRequest req, Model model){
        //봉사 신청자 이름
        Long  userNumber = (Long)req.getSession().getAttribute("userNumber");
        String userName = donateListService.selectKakaoUserName(userNumber);

        model.addAttribute("userName", userName);

            return "cs/csComplete/csComplete";
        }
}
