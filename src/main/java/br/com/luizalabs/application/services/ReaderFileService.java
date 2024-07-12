package br.com.luizalabs.application.services;

import br.com.luizalabs.adapter.persistence.models.FileData;
import br.com.luizalabs.domain.enums.StatusFile;
import br.com.luizalabs.adapter.persistence.repository.FileRepository;
import br.com.luizalabs.application.usecases.IReaderFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReaderFileService implements IReaderFile {

    private final FileRepository fileRepository;

    public ReaderFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public void reader(String pathFile) {
        try {
            Long lineNumber  = 0L;
            Path path = findFirstFile(pathFile);
            if(path != null){
                List<String> line = Files.readAllLines(path);
                for (String s : line) {
                    FileData fileData = new FileData(null, path.getFileName().toString(),s,++lineNumber, LocalDate.now(), StatusFile.PENDING);
                    fileRepository.persist(fileData);
            }

            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private Path findFirstFile(String pathFile) {
        Path path = Paths.get(pathFile);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path,"*.txt")) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    String fileName = removeExtension(file.getFileName().toString());

                    Path newPath = path.resolve(fileName + ".PROCESSED");
                    Files.move(file, newPath, StandardCopyOption.REPLACE_EXISTING);
                    return newPath;
                }
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println("Erro ao listar arquivos: " + ex.getMessage());
        }
        return null;
    }

    private String removeExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            return filename;
        }
        return filename.substring(0, lastIndexOfDot);
    }

}
