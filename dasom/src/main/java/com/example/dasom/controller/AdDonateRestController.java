package com.example.dasom.controller;

import com.example.dasom.domain.vo.DonateUserVo;
import com.example.dasom.service.AdDonateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adDonateRest")
@Slf4j
public class AdDonateRestController {

    private final AdDonateService adDonateService;

//    후원금 리스트 가져오기
    @GetMapping(value = "/find/{donateWriteNumber}")
    public List<DonateUserVo> findDonate(@PathVariable("donateWriteNumber") Long donateWriteNumber) throws Exception{
        log.info("=============" + donateWriteNumber);
        List<DonateUserVo> list = adDonateService.findDonate(donateWriteNumber);

        log.info(list.toString());
        return adDonateService.findDonate(donateWriteNumber);
    }

//    후원글에 모금된 후원금 총액 불러오기
    @GetMapping(value = "/findAmount/{donateWriteNumber}")
    public Map<String, String> findAmount(@PathVariable("donateWriteNumber") Long donateWriteNumber) throws Exception{
        log.info("=============" + donateWriteNumber);
        int amount = adDonateService.findAmount(donateWriteNumber);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedAmount = decimalFormat.format(amount);

        log.info("모금액 : " + formattedAmount);

        Map<String, String> result = new HashMap<>();
        result.put("formattedAmount", formattedAmount);
        return result;
    }


}
