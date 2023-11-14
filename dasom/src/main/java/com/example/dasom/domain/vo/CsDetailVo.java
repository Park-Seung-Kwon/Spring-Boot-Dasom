package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CsDetailVo {
    private Long csWriteNumber;
    private Long adminNumber;
    private String csWriteTitle;
    private String csWriteRecruitStart;
    private String csWriteRecruitEnd;
    private String csWriteCount;
    private String csWritePlace;
    private String csWriteActStart;
    private String csWriteActEnd;
    private Long csWriteStatus;
    private String csWriteDate;
    private String csWriteManager;
    private String csWritePhone;

   private Long csFileNumber;
   private String csFileName;
   private String csFileUploadPath;
   private String csFileUuid;



}
