package com.example.dasom.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DonateWriteVo {

    private Long donateWriteNumber;
    private Long adminNumber;
    private String donateWriteTitle;
    private String donateWriteStatus;
    private Long donateFileNumber;
    private String donateFileName;
    private String donateFileUploadPath;
    private String donateFileUuid;


}
