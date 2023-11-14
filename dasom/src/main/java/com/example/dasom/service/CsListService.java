package com.example.dasom.service;

import com.example.dasom.domain.dto.CsDto;
import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.CsDetailVo;
import com.example.dasom.domain.vo.DonateListVo;
import com.example.dasom.mapper.CsListMapper;
import com.example.dasom.mapper.DonateListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsListService {
    private final CsListMapper csListMapper;

//    봉사 페이지 전체 게시글 수 조회
    public int getTotal(String status){
        return csListMapper.selectTotal(status);
    }


//    봉사 리스트 전체 게시글 조회
    public List<CsWriteDto> showList(Criteria criteria, String status){
        return csListMapper.selectList(criteria, status);

    };

//    봉사 게시물 상세보기
    public CsDetailVo showDetail(Long csNum){
       return csListMapper.selectDetail(csNum);
    };

//    봉사 신청 정보 DB테이블에 입력
    public boolean csApply(Long userNumber, Long csWriteNumber){

        //중복 체크
        Integer duplicateCheck = csListMapper.selectCsDuplicate(userNumber,csWriteNumber);

        //중복일 경우
        if(duplicateCheck>0) {
            return true;
        }
        // 중복 아닐경우 DB에 정보입력
        csListMapper.insert(userNumber, csWriteNumber);
        return false;

    };

}
