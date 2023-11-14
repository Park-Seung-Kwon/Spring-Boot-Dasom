package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdDonateVo {
    private Long donateNumber;
    private String donateWriteTitle;
    private String userId;
    private String userName;
    private Long donateAmount;
    private String donateDate;
}
