package com.example.dasom.mapper;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.DonateWriteVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainPageMapper {

//    메인페이지 후원글 가져오기
    public List<DonateWriteVo> selectDonate();

//    메이페이지 봉사글 가져오기
    public List<CsWriteDto> selectCs();

    //    메인페이지 누적 후원금 가져오기
    public int selectAmount();

}
