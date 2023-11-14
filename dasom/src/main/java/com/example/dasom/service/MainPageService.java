package com.example.dasom.service;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.DonateWriteVo;
import com.example.dasom.mapper.MainPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MainPageService {

    private final MainPageMapper mainPageMapper;

//    메인페이지 후원 글 가져오기
    public List<DonateWriteVo> findDonateAll(){

        List<DonateWriteVo> list = new ArrayList<>();
        list = mainPageMapper.selectDonate();
        for(int i=0; i<list.size(); i++){
            log.info(String.valueOf(list.get(i)));
        }

        return mainPageMapper.selectDonate();
    }

//    메인페이지 봉사 글 가져오기
    public List<CsWriteDto> findCsAll(){

        List<CsWriteDto> list = new ArrayList<>();
        list = mainPageMapper.selectCs();
        for(int i=0; i< list.size(); i++){
            log.info(String.valueOf(list.get(i)));
        }

        return mainPageMapper.selectCs();
    }

//    메인페이지 누적 후원금 가져오기
    public int findAmount(){
        return mainPageMapper.selectAmount();
    }

}
