package com.example.dasom.service;

import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.dto.DonateWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.CsWriteVo;
import com.example.dasom.domain.vo.SearchVo;
import com.example.dasom.mapper.CsWriteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CsWriteService {

    private final CsWriteMapper csWriteMapper;
    private final CsFileService csFileService;

//    봉사 글 작성
    public void register(CsWriteDto csWriteDto){
        csWriteMapper.insert(csWriteDto);
    }

//    봉사 글 작성(이미지 파일 포함)
    public void registerAndFileProc(CsWriteDto csWriteDto, MultipartFile file){
            register(csWriteDto);

            if(file.isEmpty()){return;}

            try {
                csFileService.registerAndSaveFile(file, csWriteDto.getCsWriteNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    //    봉사글 전체 조회
    public List<CsWriteDto> findAll(Criteria criteria, SearchVo searchVo){

        List<CsWriteDto>list = new ArrayList<>();
        list = csWriteMapper.selectAll(criteria, searchVo);
        for(int i=0; i<list.size(); i++){
            log.info(String.valueOf(list.get(i)));
        }

        return csWriteMapper.selectAll(criteria, searchVo);
    }
    //    봉사글 총 개수 조회
    public int getTotal(SearchVo searchVo){
        return csWriteMapper.selectTotal(searchVo);
    }

//    봉사글 상세조회(수정페이지 이동)
    public CsWriteVo find(Long csWriteNumber){
        if(csWriteNumber == null){
            throw new IllegalArgumentException("봉사 글 번호 누락!!");
        }
        return Optional.ofNullable(csWriteMapper.select(csWriteNumber))
                .orElseThrow(() -> {throw  new IllegalArgumentException("봉사 글 번호 누락!!");});
    }

//    봉사 글 삭제
    public void remove(Long csWriteNumber){
        if(csWriteNumber == null){
            throw new IllegalArgumentException("봉사 글 번호 누락!");
        }
        csFileService.remove(csWriteNumber);
        csWriteMapper.delete(csWriteNumber);
    }

//    봉사 글 수정
    public void modify(CsWriteDto csWriteDto, MultipartFile file){
        if(!file.isEmpty()){

            csFileService.remove(csWriteDto.getCsWriteNumber());
            try {
                csFileService.registerAndSaveFile(file,csWriteDto.getCsWriteNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        csWriteMapper.update(csWriteDto);
    }

//    봉사 글 모집 완료
    public void recruit(Long csWriteNumber){
        if(csWriteNumber == null){
            throw new IllegalArgumentException("후원글 번호 누락!");
        }
        csWriteMapper.updateStatus(csWriteNumber);
    }

}


