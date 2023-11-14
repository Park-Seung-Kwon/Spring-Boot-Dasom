package com.example.dasom.mapper;

import com.example.dasom.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShelterMapper {

//    대피소 안내 회원 이름, 주소 가져오기
    public UserDto select(Long userNumber);
}
