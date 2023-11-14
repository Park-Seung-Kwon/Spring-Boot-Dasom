package com.example.dasom.service;

import com.example.dasom.domain.dto.DonateDto;
import com.example.dasom.mapper.DonateListMapper;
import com.example.dasom.mapper.DonateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DonateService {
    private final DonateMapper donateMapper;
    private final DonateListMapper donateListMapper;

    @Transactional
    public void donation(DonateDto donateDto){
        donateMapper.insert(donateDto);
    }

    //     카카오페이 페이지에 입력될 유저이름 출력
    public String selectName(Long userNumber){
        return donateMapper.selectName(userNumber);
    };
    //      카카오페이 페이지에 입력될 봉사 이름 출력
    public String selectDonateWriteTitle(Long donateWriteNumber){
        return donateListMapper.selectDonateWriteTitle(donateWriteNumber);
    }


}
