package com.example.dasom.mapper;

import com.example.dasom.domain.dto.CsDto;
import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.vo.CsDetailVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsListMapperTest {

    @Autowired
    CsListMapper csListMapper;

    CsDto csDto;

    @Test
    void selectList() {
        List<CsWriteDto> dto = csListMapper.selectList("0");
        Assertions.assertThat(dto.size()).isEqualTo(10);
    }

    @Test
    void selectDetail() {

        CsDetailVo cv = csListMapper.selectDetail(52L);
        Assertions.assertThat(cv.getAdminNumber()).isEqualTo(22);
    }
}