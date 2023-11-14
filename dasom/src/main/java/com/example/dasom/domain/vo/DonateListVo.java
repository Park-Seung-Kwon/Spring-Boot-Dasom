package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class DonateListVo {

    private Long donatewriteNumber;
    private Long adminNumber;
    private String donatewriteTitle;
    private String donatewriteStatus;
    private Date donatewriteDate;
    private Long donatefileNumber;
    private String donatefileName;
    private String donatefileUploadPath;
    private String donatefileUuid;
}
