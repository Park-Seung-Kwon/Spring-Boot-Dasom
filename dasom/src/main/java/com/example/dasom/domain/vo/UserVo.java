package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class UserVo {

    //    donate_number,TBL_DONATE.user_number,TBL_DONATEWRITE.donatewrite_title,donate_amount,donate_date
    Long donateNumber;
    Long userNumber;
    Long donateWriteNumber;
    Long donateAmount;
    String donateDate;
    Long adminNumber;
    String donateWriteTitle;
    String donateWriteStatus;
    String donateWriteDate;
    Long csNumber;
    Long csWriteNumber;
    String csApplyDate;
    String csWriteTitle;
    String csWriteRecruitStart;
    String csWriteRecruitEnd;
    String csWriteCount;
    String csWritePlace;
    String csWriteActStart;
    String csWriteActEnd;
    Long csWriteStatus;
    String csWriteDate;
    String csWriteManager;
    String csWritePhone;
    Long donateCount;
    String totalDonateAmount;


}

