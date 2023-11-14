package com.example.dasom.service;

import com.example.dasom.domain.dto.CsFileDto;
import com.example.dasom.domain.dto.CsWriteDto;
import com.example.dasom.domain.dto.DonateFileDto;
import com.example.dasom.mapper.CsFileMapper;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CsFileService {

    private final CsFileMapper csFileMapper;

    @Value("${file.dir}")
    private String fileDir;

//    봉사 글 이미지 업로드
    public void register(CsFileDto csFileDto){ csFileMapper.insert(csFileDto);}

    @Transactional
    //    파일 저장 처리
    public CsFileDto saveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = file.getOriginalFilename();
//        파일이름에 붙여줄 uuid를 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

//        상위 경로와 하위경로를 합친다.
        File uploadPath = new File(fileDir, getUploadPath());

//        경로가 존재하지 않는다면 (폴더가 없다면)
        if(!uploadPath.exists()){
//            경로를 만들어준다.(폴더를 만든다)
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath,sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
        file.transferTo(uploadFile);

//        썸내일을 저장한다
//        이미지 파일인 경우에만 썸네일을 저장해야한다.
        if(Files.probeContentType(uploadFile.toPath()).startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_" + sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 400, 340);
            out.close();
        }

        CsFileDto csFileDto = new CsFileDto();
        csFileDto.setCsFileName(originName);
        csFileDto.setCsFileUuid(uuid.toString());
        csFileDto.setCsFileUploadPath(getUploadPath());

        return csFileDto;
    }

    /**
     * 파일 DB등록 및 파일 저장 처리
     *
     * @param file 파일을 담은 파라미터
     * @param csWriteNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void registerAndSaveFile(MultipartFile file, Long csWriteNumber) throws IOException{

        CsFileDto csFileDto = saveFile(file);
        csFileDto.setCsWriteNumber(csWriteNumber);
        register(csFileDto);

    }


    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

//    삭제
    public void remove(Long csWriteNumber){
        if(csWriteNumber == null){
            throw new IllegalArgumentException("봉사글 번호 누락!");
        }

        CsFileDto file = find(csWriteNumber);

        File target = new File(fileDir, file.getCsFileUploadPath() + "/" + file.getCsFileUuid() + "_" + file.getCsFileName());
        File thumbnail = new File(fileDir, file.getCsFileUploadPath() + "/th_" + file.getCsFileUuid() + "_" + file.getCsFileName());

        if (target.exists()) {
            target.delete();
        }

        if (thumbnail.exists()) {
            thumbnail.delete();
        }

        csFileMapper.delete(csWriteNumber);
    }

//    파일 조회
    public CsFileDto find(Long csWriteNumber){
        if(csWriteNumber == null){
            throw new IllegalArgumentException("봉사글 번호 누락!!");
        }

        return csFileMapper.select(csWriteNumber);
    }

}
