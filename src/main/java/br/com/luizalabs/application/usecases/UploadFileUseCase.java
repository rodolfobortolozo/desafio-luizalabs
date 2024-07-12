package br.com.luizalabs.application.usecases;

import br.com.luizalabs.infrastructure.exceptions.FileException;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public interface UploadFileUseCase {

    String[] EXTENSIONS_FILE = {".txt"};

    Boolean uploadMultipleFiles(List<MultipartFile> files);

    default void invalidExtension(String filename) {
        boolean isValidExtension = Arrays.stream(EXTENSIONS_FILE)
                .anyMatch(extension -> filename.toLowerCase().endsWith(extension));

        if (!isValidExtension) {
            throw new FileException("Arquivo Inválido: " + filename);
        }
    }

    default String generateFileName(String originalFilename) {
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        return name+"_"+timestamp+extension;
    }
}
