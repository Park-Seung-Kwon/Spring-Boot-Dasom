package com.example.dasom.service;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.mapper.ShelterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShelterService {

    private final ShelterMapper shelterMapper;

//    대피소 안내 회원 이름, 주소 가져오기
    public UserDto find(Long userNumber){
        if(userNumber == null){
            throw new IllegalArgumentException("회원 번호 누락!");
        }
        return Optional.ofNullable(shelterMapper.select(userNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("회원 번호 누락!");});
    }
}
