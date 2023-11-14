package com.example.dasom.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class DonateDto {

    private Long donateNumber;
    private Long userNumber;
    private Long donateWriteNumber;
    private Long donateAmount;
    private Date donateDate;

}
