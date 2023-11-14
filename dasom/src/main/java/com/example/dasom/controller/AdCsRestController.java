package com.example.dasom.controller;

import com.example.dasom.domain.vo.CsUserVo;
import com.example.dasom.service.AdCsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adCsRest")
@Slf4j
public class AdCsRestController {

    private final AdCsService adCsService;

//    봉사자 리스트 가져오기
    @GetMapping(value = "/find/{csWriteNumber}")
    public List<CsUserVo> findUser(@PathVariable("csWriteNumber") Long csWriteNumber) throws Exception{
        log.info("================" +csWriteNumber);
        List<CsUserVo> list = adCsService.findUser(csWriteNumber);

        log.info(list.toString());
        return adCsService.findUser(csWriteNumber);

    }
}
