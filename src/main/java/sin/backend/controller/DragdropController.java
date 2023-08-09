package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sin.backend.domain.FileUp;
import sin.backend.service.FileService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("drag_drop")
public class DragdropController {
    private FileService fileService;
    //(1) file upload
    @GetMapping("form_dd.do")
    public String form(){
       return "drag_drop/form";
    }
    @PostMapping("upload.do")
    public String uploadFile(MultipartHttpServletRequest mhsr)throws IOException{
        Iterator<String> itr =mhsr.getFileNames();
        while(itr.hasNext()){
            MultipartFile mpf = mhsr.getFile(itr.next());
            fileService.saveFile(mpf);
        }
        String appendData = mhsr.getParameter("temp");
        System.out.println("@@DragdropController uploadFile() appendData: "+appendData);
        return "redirect:../";
    }
    //(2) 리스팅
    @GetMapping("list.do")
    public String view(Model model){
        List<FileUp> fileups = fileService.getFileUpAll();
        model.addAttribute("fileups", fileups);
        return "drag_drop/list";
    }
    //(3) 첨부파일 다운로드
    @GetMapping("/attach/{file_id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable long file_id) throws MalformedURLException {
        FileUp fileup = fileService.getFileUp(file_id);
        UrlResource resource = new UrlResource("file:" + fileup.getSavedpath());
        String encodedFileName = org.springframework.web.util.UriUtils.encode(fileup.getOrgnm(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
    //(4) 삭제
    @GetMapping("remove.do")
    public String remove(long id){
        System.out.println("@@DragdropController remove() id: "+id);
        //fileService. //삭제 추가
        return "redirect:list.do";
    }
}
