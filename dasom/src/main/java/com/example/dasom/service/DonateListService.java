package com.example.dasom.service;

import com.example.dasom.domain.dto.DonateDto;
import com.example.dasom.domain.dto.DonateWriteDto;
import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.DonateListVo;
import com.example.dasom.mapper.DonateListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonateListService {
    private final DonateListMapper donateListMapper;

//    기부 페이지 전체 게시글 수 조회
    public int getTotal(String status){
        return donateListMapper.selectTotal(status);
    }


//    기부 리스트 전체 게시글 조회
    public List<DonateListVo> showList(Criteria criteria, String status){
        return donateListMapper.selectList( criteria, status);
    };

//     카카오페이 페이지에 입력될 유저이름 출력
    public String selectKakaoUserName(Long userNumber){
        return donateListMapper.selectKakaoUserName(userNumber);
    };

}
