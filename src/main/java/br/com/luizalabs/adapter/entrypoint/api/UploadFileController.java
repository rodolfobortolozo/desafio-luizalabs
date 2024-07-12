package br.com.luizalabs.adapter.entrypoint.api;

import br.com.luizalabs.application.usecases.UploadFileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    private final UploadFileUseCase uploadFile;

    public UploadFileController(UploadFileUseCase uploadFile) {
        this.uploadFile = uploadFile;
    }

    @PostMapping("/v1/files")
    public ResponseEntity<Boolean> uploadMultipleFiles(@RequestParam("file") List<MultipartFile> files) {

        return ResponseEntity.ok(uploadFile.uploadMultipleFiles(files));
    }
}
