package com.example.dasom.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class CsWriteDto {
    private Long csWriteNumber;
    private Long adminNumber;
    private String csWriteTitle;
    private String csWriteRecruitStart;
    private String csWriteRecruitEnd;
    private String csWriteCount;
    private String csWritePlace;
    private String csWriteActStart;
    private String csWriteActEnd;
    private String csWriteStatus;
    private String csWriteDate;
    private String csWriteManager;
    private String csWritePhone;
}
