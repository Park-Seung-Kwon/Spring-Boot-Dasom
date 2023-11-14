package com.example.dasom.mapper;

import com.example.dasom.domain.dto.CsFileDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsFileMapper {
//    봉사글 작성 이미지 업로드
    public void insert(CsFileDto csFileDto);
//    봉사 글 이미지 삭제
    public void delete(Long csWriteNumber);
//    봉사 글 이미지 조회
    public CsFileDto select(Long csWriteNumber);
}
