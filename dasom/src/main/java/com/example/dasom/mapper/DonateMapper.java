package com.example.dasom.mapper;

import com.example.dasom.domain.dto.DonateDto;
import com.example.dasom.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonateMapper {
    //    카카오페이 페이지에 입력될 유저이름
    public String selectName(Long userNumber);

    //    후원금
    public void insert(DonateDto donateDto);


}
