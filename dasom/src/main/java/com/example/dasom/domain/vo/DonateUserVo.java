package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class DonateUserVo {
    private Long donateNumber;
    private String userId;
    private String userName;
    private Long donateWriteNumber;
    private Long donateAmount;
    private String donateDate;
}
