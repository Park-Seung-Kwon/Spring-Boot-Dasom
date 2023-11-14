package com.example.dasom.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

//    관리자 번호 조회(관리자 로그인)
    public Long selectAdminNumber(@Param("adminId")String adminId, @Param("adminPassword")String adminPassword);
}
