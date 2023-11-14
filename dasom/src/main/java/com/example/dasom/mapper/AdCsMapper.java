package com.example.dasom.mapper;

import com.example.dasom.domain.vo.CsUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdCsMapper {

//    봉사자 리스트 조회
    public List<CsUserVo> selectCsUser(Long csWriteNumber);
}
