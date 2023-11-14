package com.example.dasom.controller;

import com.example.dasom.domain.dto.DonateFileDto;
import com.example.dasom.service.DonateFileService;
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
@RequestMapping("/donateFile/*")
@Slf4j
public class DonateFileController {
    private final DonateFileService donateFileService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/imgList")
    public DonateFileDto imgList(Long donateWriteNumber){
        return donateFileService.find(donateWriteNumber);
    }

    @GetMapping("/display")
    public byte[] display(String fileName)throws IOException{

        return FileCopyUtils.copyToByteArray(new File(fileDir, fileName));
    }
}
