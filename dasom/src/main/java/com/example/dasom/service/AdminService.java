package com.example.dasom.service;

import com.example.dasom.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminMapper adminMapper;

    public Long selectAdminNumber(String adminId, String adminPassword){
        return Optional.ofNullable(adminMapper.selectAdminNumber(adminId, adminPassword))
                .orElseThrow(() -> {
                   throw new IllegalArgumentException("일치하는 관리자 정보 없음!");
                });
    }

}
