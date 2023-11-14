package com.example.dasom.controller;

import com.example.dasom.domain.dto.CsFileDto;
import com.example.dasom.service.CsFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csFile/*")
@Slf4j
public class CsFileController {
    private final CsFileService csFileService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/imgList")
    public CsFileDto imgList(Long csWriteNumber){
        return csFileService.find(csWriteNumber);
    }

    @GetMapping("/display")
    public byte[] display(String fileName)throws IOException{

        return FileCopyUtils.copyToByteArray(new File(fileDir, fileName));
    }

}
