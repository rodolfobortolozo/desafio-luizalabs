package br.com.luizalabs.adapter.entrypoint.api;

import br.com.luizalabs.application.usecases.UploadFileUseCase;
import br.com.luizalabs.infrastructure.exceptions.FileException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;


@ExtendWith({MockitoExtension.class})
@WebMvcTest(UploadFileController.class)
public class UploadFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UploadFileUseCase uploadFile;

    @Test
    @DisplayName("Deve enviar os arquivos e retornar verdadeiro")
    @Order(1)
    public void whenUploadFileandReturnTrue() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "file".getBytes());

        Mockito.when(uploadFile.uploadMultipleFiles(Collections.singletonList(file))).thenReturn(Boolean.TRUE);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/v1/files")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

}