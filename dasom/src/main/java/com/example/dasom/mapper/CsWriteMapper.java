package com.example.dasom.mapper;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.dto.DonateWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.CsWriteVo;
import com.example.dasom.domain.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CsWriteMapper {

//    봉사글 작성
    public void insert(CsWriteDto csWriteDto);
//    봉사글 전체 조회
    public List<CsWriteDto> selectAll(Criteria criteria, SearchVo searchVo);
//    전체 봉사글 수 조회
    public int selectTotal(SearchVo searchVo);
//    봉사 글 조회
    public CsWriteVo select(@Param("csWriteNumber") Long csWriteNumber);
//    봉사 글 삭제
    public void delete(Long csWriteNumber);
//    봉사 글 수정
    public void update(CsWriteDto csWriteDto);
//    봉사 글 글 모집 완료
    public void updateStatus(Long csWriteNumber);
}
