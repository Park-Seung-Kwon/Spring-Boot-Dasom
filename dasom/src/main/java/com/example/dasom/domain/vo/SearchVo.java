package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
//@NoArgsConstructor
public class SearchVo {
    private String cate;
    private String keyword;

    public SearchVo() {
        this.cate = "";
        this.keyword = "";
    }

}
