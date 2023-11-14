package com.example.dasom.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DonateFileDto {
    private Long donateFileNumber;
    private Long donateWriteNumber;
    private String donateFileName;
    private String donateFileUploadPath;
    private String donateFileUuid;
}
