package com.example.dasom.mapper;

import com.example.dasom.domain.vo.DonateListVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class DonateListMapperTest {

    @Autowired
    DonateListMapper donateListMapper;

    DonateListVo donateListVo;

    @Test
    void selectTotal() {
        int num = donateListMapper.selectTotal("1");
        Assertions.assertThat(num).isEqualTo(2);

    }

//    @Test
//    void selectList() {
//        List<DonateListVo> donateListVo =  donateListMapper.selectList("1");
//        assertThat(donateListVo.size()).isEqualTo(1);
//    }


}