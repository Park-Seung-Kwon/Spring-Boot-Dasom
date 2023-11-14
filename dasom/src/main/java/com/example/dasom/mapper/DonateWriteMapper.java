package com.example.dasom.mapper;

import com.example.dasom.domain.dto.DonateWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.DonateWriteVo;
import com.example.dasom.domain.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonateWriteMapper {

//    후원글 작성
    public void insert(DonateWriteDto donateWriteDto);
//    후원글 전체 조회
    public List<DonateWriteDto> selectAll(Criteria criteria, SearchVo searchVo);
//    전체 후원글 수 조회
    public int selectTotal(SearchVo searchVo);
//    후원 글 조회
    public DonateWriteVo select(@Param("donateWriteNumber") Long donateWriteNumber);
//    후원글 삭제
    public void delete(Long donateWriteNumber);
//    후원 글 수정
    public void update(DonateWriteDto donateWriteDto);
//    후원 글 모집 완료
    public void updateStatus(Long donateWriteNumber);

}
