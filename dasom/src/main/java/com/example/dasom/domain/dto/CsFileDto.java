package com.example.dasom.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CsFileDto {
    private Long csFileNumber;
    private Long csWriteNumber;
    private String csFileName;
    private String csFileUploadPath;
    private String csFileUuid;
}
