package com.example.dasom.controller.donate;

import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.DonateListVo;
import com.example.dasom.domain.vo.PageVo;
import com.example.dasom.service.DonateListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donations")
@Slf4j
public class DonateListRestController {
    private final DonateListService donateListService;


//    후원글 리스트 출력 (페이징 적용)
    @GetMapping("/{status}/{page}")
    public Map<String, Object> replyListPage(@PathVariable("page")Integer page, @PathVariable("status") String status){
        Criteria criteria = new Criteria();
        criteria.setPage(page);

        PageVo pageVo = new PageVo(donateListService.getTotal(status), criteria);
        List<DonateListVo> donateListVoList = donateListService.showList(criteria, status);

        Map<String, Object> donateListMap = new HashMap<>();
        donateListMap.put("pageVo", pageVo);
        donateListMap.put("donateListVoList", donateListVoList);

        return donateListMap;
    }


}
