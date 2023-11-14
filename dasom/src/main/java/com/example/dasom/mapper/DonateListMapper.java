package com.example.dasom.mapper;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.DonateListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonateListMapper {

    //     기부 리스트 전체 게시글 개수 조회
    public int selectTotal(String status);

    //   기부 리스트 조회(페이징 처리)
    public List<DonateListVo> selectList(@Param("criteria") Criteria criteria, String status);

    //    카카오페이 페이지에 입력될 유저이름 출력
    public String selectKakaoUserName(Long UserNumber);

    //     기부 게시글 타이틀 조회
    public String selectDonateWriteTitle(Long donateWriteNumber);

}
