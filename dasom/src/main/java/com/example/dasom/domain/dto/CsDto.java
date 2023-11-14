package com.example.dasom.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class CsDto {
    private Long csNumber;
    private Long userNumber;
    private Long csWriteNumber;
    private Date csApplyDate;
}
