package com.example.dasom.service;

import com.example.dasom.domain.vo.CsUserVo;
import com.example.dasom.mapper.AdCsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdCsService {

    private final AdCsMapper adCsMapper;

    public List<CsUserVo> findUser(Long csWriteNumber){
        if(csWriteNumber == null){
            throw new IllegalArgumentException("봉사글 번호 누락!");
        }
        return Optional.ofNullable(adCsMapper.selectCsUser(csWriteNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 글 번호!");});
    }

}
