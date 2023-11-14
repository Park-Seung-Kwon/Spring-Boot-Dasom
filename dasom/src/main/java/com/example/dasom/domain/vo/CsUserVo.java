package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class CsUserVo {
    private Long csWriteNumber;
    private Long userNumber;
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String csApplyDate;


}
