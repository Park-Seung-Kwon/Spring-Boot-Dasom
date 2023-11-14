package com.example.dasom.mapper;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdUserMapper {

//    회원전체조회
    public List<UserDto> selectAll(Criteria criteria, SearchVo searchVo);
//    회원수 조회
    public int selectTotal(SearchVo searchVo);
//    회원 조회
    public UserDto select(@Param("userNumber") Long userNumber);
//    회원 정보 삭제
    public void delete(Long userNumber);

}
