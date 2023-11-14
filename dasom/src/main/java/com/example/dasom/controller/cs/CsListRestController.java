package com.example.dasom.controller.cs;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.PageVo;
import com.example.dasom.service.CsListService;
import com.example.dasom.service.DonateListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csRest")
@Slf4j
public class CsListRestController {
    private final CsListService csListService;
    private final DonateListService donateListService;


//    봉사신청 게시글 띄우기
    @GetMapping(value = {"/{status}/{page}"})
    public Map<String, Object> showList(@PathVariable("page")Integer page, @PathVariable("status")String status){
        Criteria criteria = new Criteria();
        criteria.setPage(page);

        PageVo pageVo = new PageVo(csListService.getTotal(status), criteria);
        List<CsWriteDto> CsWriteDtoList = csListService.showList(criteria, status);

        Map<String, Object> csListMap = new HashMap<>();
        csListMap.put("pageVo", pageVo);
        csListMap.put("CsWriteDtoList", CsWriteDtoList);
        return csListMap;
    }

    //    봉사신청 완료페이지
    @GetMapping("/csComplete/{csNum}")
    public boolean csComplete(@PathVariable("csNum")Long csWriteNumber, HttpServletRequest req, Model model){
        //봉사 신청자 이름
        Long  userNumber = (Long)req.getSession().getAttribute("userNumber");
        String userName = donateListService.selectKakaoUserName(userNumber);

        // 중복체크 후 기부 테이블에 DB삽입
        boolean duplicateCheck = csListService.csApply(userNumber,csWriteNumber);

      return duplicateCheck;

    }







}
