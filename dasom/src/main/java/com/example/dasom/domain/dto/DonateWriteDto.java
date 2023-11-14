package com.example.dasom.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class DonateWriteDto {

    private Long donateWriteNumber;
    private Long adminNumber;
    private String donateWriteTitle;
    private String donateWriteStatus;
    private String donateWriteDate;

}
