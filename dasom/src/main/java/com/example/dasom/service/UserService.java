package com.example.dasom.service;

import com.example.dasom.domain.dto.UserDto;
import com.example.dasom.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

//    회원가입
    public void register(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원 정보가 누락됨!!!");
        }

        userMapper.insert(userDto);
    }

//    회원가입 로그인 중복 확인
    public int checkId(String userId) throws Exception {
    return userMapper.checkId(userId);
    }

    //    로그인
    public UserDto find(String userId, String userPassword){
        return Optional.ofNullable(userMapper.select(userId, userPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("회원 조회 결과 없음"); });
    }




}
