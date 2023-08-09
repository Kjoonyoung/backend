package sin.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sin.backend.domain.FileUp;
import sin.backend.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.dir}")
    private String fileDir;
    private final FileRepository fileRepository;
    //(1) 업로드
    @Override
    public long saveFile(MultipartFile mf) throws IOException {
        if(mf.isEmpty()){
            return -1;
        }
        String origName = mf.getOriginalFilename(); //원래 파일 이름 추출
        String uuid = UUID.randomUUID().toString(); //파일 이름으로 쓸 UUID 생성 (암호화)
        String extension = origName.substring(origName.lastIndexOf(".")); //확장자추출(ex: .png)
        String savedName = uuid+extension; //uuid + 확장자  FKWDXJXXSXXXX == UUID
        String savedPath = fileDir +savedName; //파일 불러올 때 사용할 파일 경로

        FileUp fileUp = FileUp.builder()
                .orgnm(origName).savednm(savedName)
                .savedpath(savedPath).build();
        mf.transferTo(new File(savedPath)); //실제 로컬에 uuid를 파일명으로 저장
        FileUp saveFile = fileRepository.save(fileUp); //DB에 파일정보 저장

        return saveFile.getId();
    }
    //(2) 다운로드
    @Override
    public List<FileUp> getFileUpAll() {
        List<FileUp> fileUps = fileRepository.findAll();
        return fileUps;
    }

    @Override
    public FileUp getFileUp(long file_id) {
        FileUp fileUp = fileRepository.findById(file_id).orElse(null);
        return fileUp;
    }
}
