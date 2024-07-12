package br.com.luizalabs.application.services;

import br.com.luizalabs.infrastructure.exceptions.FileException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadFileUseCaseServiceTest {

    @Mock
    private UploadFileUseCaseService fileService;

    @Test
    @DisplayName("Deve fazer upload de multiplos arquivos")
    @Order(1)
    void testUploadMultipleFiles() throws IOException {
        MockMultipartFile file1 = new MockMultipartFile("file", "test1.txt", "text/plain", "some content".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "test2.txt", "text/plain", "some other content".getBytes());
        List<MultipartFile> files = List.of(file1, file2);

        when(fileService.uploadMultipleFiles(files)).thenCallRealMethod();
        Mockito.doNothing().when(fileService).invalidExtension(anyString());
        when(fileService.generateFileName(anyString())).thenReturn("generatedFileName.txt");

        boolean result = fileService.uploadMultipleFiles(files);

        assertTrue(result);
    }

    @Test
    @DisplayName("Deve verificar se a lista de arquivos para upload esta vazia")
    @Order(2)
    void testUploadMultipleFilesEmptyList() {
        List<MultipartFile> files = List.of();

        when(fileService.uploadMultipleFiles(files)).thenCallRealMethod();

        FileException exception = assertThrows(FileException.class, () -> {
            fileService.uploadMultipleFiles(files);
        });

        assertEquals("Lista está vazia.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornara Exception com a men")
    @Order(3)
    void testException(){

        MockMultipartFile file1 = new MockMultipartFile("file", "test1.txt", "text/plain", "some content".getBytes());

        List<MultipartFile> files = List.of();

        doThrow(new FileException("Erro ao Importar Arquivos."))
                .when(fileService).uploadMultipleFiles(files);

        FileException exception = assertThrows(FileException.class, () -> {
            fileService.uploadMultipleFiles(files);
        });

        assertEquals("Erro ao Importar Arquivos.", exception.getMessage());
    }

}