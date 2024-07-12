package br.com.luizalabs.application.services;

import br.com.luizalabs.infrastructure.exceptions.FileException;
import br.com.luizalabs.application.usecases.UploadFileUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UploadFileUseCaseService implements UploadFileUseCase {

    @Value("${uploaded_folder}")
    private String uploadedFolder;

    @Override
    public Boolean uploadMultipleFiles(List<MultipartFile> files) {

        if(files.isEmpty()) throw new FileException("Lista está vazia.");

        try{
            for(MultipartFile file : files){

                invalidExtension(files.getFirst().getOriginalFilename());

                byte[] bytes = file.getBytes();
                String fileName = generateFileName(file.getOriginalFilename());
                Path path = Paths.get(uploadedFolder + fileName);
                Files.write(path, bytes);
            }
            return true;
        } catch (IOException e) {
            throw new FileException("Erro ao Importar Arquivos.");
        }

    }
}
