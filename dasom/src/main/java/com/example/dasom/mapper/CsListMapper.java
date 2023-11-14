package com.example.dasom.mapper;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.CsDetailVo;
import com.example.dasom.domain.vo.DonateListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CsListMapper {

    //    봉사 리스트 전체 게시글 개수 조회
    public int selectTotal(String status);

    //         기부 리스트 전체 게시글 조회 (페이징처리)
    public List<CsWriteDto> selectList(@Param("criteria") Criteria criteria, String status);


    //    봉사 게시글 상세보기 입력될 정보조회
    public CsDetailVo selectDetail(Long csNum);

    //    봉사 신청 정보 DB테이블에 입력
    public void insert(Long userNumber, Long csWriteNumber);

    //봉사 중복신청 체크
    public Integer selectCsDuplicate(Long userNumber, Long csWriteNumber);
}
