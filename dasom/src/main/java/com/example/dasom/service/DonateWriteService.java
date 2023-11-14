package com.example.dasom.service;

import com.example.dasom.domain.dto.DonateWriteDto;
import com.example.dasom.domain.vo.Criteria;
import com.example.dasom.domain.vo.DonateWriteVo;
import com.example.dasom.domain.vo.SearchVo;
import com.example.dasom.mapper.DonateWriteMapper;
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
public class DonateWriteService {

    private final DonateWriteMapper donateWriteMapper;
    private final DonateFileService donateFileService;

//    후원 글 작성
    public void register(DonateWriteDto donateWriteDto){
        donateWriteMapper.insert(donateWriteDto);
    }

//    후원 글 작성(이미지 파일 포함)
    public void registerAndFileProc(DonateWriteDto donateWriteDto, MultipartFile file){
        register(donateWriteDto);

        if(file.isEmpty()){return;}

        try {
            donateFileService.registerAndSaveFile(file, donateWriteDto.getDonateWriteNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    후원글 전체 조회
    public List<DonateWriteDto> findAll(Criteria criteria, SearchVo searchVo){

        List<DonateWriteDto>list = new ArrayList<>();
        list = donateWriteMapper.selectAll(criteria, searchVo);
        for(int i=0; i<list.size(); i++){
            log.info(String.valueOf(list.get(i)));
        }

        return donateWriteMapper.selectAll(criteria, searchVo);
    }
//    후원글 총 개수 조회
    public int getTotal(SearchVo searchVo){
    return donateWriteMapper.selectTotal(searchVo);
}

//    후원글 상세조회(수정페이지 이동)
    public DonateWriteVo find(Long donateWriteNumber){
        if(donateWriteNumber == null){
            throw new IllegalArgumentException("후원글 번호 누락!");
        }
        return Optional.ofNullable(donateWriteMapper.select(donateWriteNumber))
                .orElseThrow(() -> { throw new IllegalArgumentException("존재하지 않는 글 번호!"); });
    }

//    후원 글 삭제
    public void remove(Long donateWriteNumber){
        if(donateWriteNumber == null){
            throw new IllegalArgumentException("후원글 번호 누락!");
        }
        donateFileService.remove(donateWriteNumber);
        donateWriteMapper.delete(donateWriteNumber);
    }

//    후원 글 수정
    public void modify(DonateWriteDto donateWriteDto, MultipartFile file){
        if(!file.isEmpty()){

            donateFileService.remove(donateWriteDto.getDonateWriteNumber());
            try {
                donateFileService.registerAndSaveFile(file,donateWriteDto.getDonateWriteNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        donateWriteMapper.update(donateWriteDto);
    }
//    후원 글 모집완료
    public void recruit(Long donateWriteNumber){
        if(donateWriteNumber == null){
            throw new IllegalArgumentException("후원글 번호 누락!");
        }
        donateWriteMapper.updateStatus(donateWriteNumber);

    }


}
