package com.example.dasom.service;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.SearchVo;
import com.example.dasom.mapper.AdUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdUserService {

    private final AdUserMapper adUserMapper;

//    회원 전체 조회
    public List<UserDto> findAll(Criteria criteria, SearchVo searchVo){

        List<UserDto>list = new ArrayList<>();
        list = adUserMapper.selectAll(criteria,searchVo);
        for(int i=0; i<list.size(); i++){
            log.info(String.valueOf(list.get(i)));
        }

        return adUserMapper.selectAll(criteria,searchVo);
    }

//    회원 인원 조회(검색 포함)
    public int getTotal(SearchVo searchVo){
        return adUserMapper.selectTotal(searchVo);
    }

//    회원 상세 조회
    public UserDto find(Long userNumber){
        if(userNumber == null){
            throw new IllegalArgumentException("회원 번호 누락!");
        }
        return Optional.ofNullable(adUserMapper.select(userNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("회원 번호 누락!");});
    }

//    회원 정보 삭제
    public void remove(Long userNumber){
        if(userNumber == null){
            throw new IllegalArgumentException("회원 번호 누락!");
        }
        adUserMapper.delete(userNumber);
    }
}
