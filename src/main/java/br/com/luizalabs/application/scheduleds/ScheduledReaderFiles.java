package br.com.luizalabs.application.scheduleds;

import br.com.luizalabs.application.services.ReaderFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScheduledReaderFiles extends ScheduledTasks {

    @Value("${uploaded_folder}")
    private String uploadedFolder;

    private final ReaderFileService readerFileService;

    public ScheduledReaderFiles(ReaderFileService readerFileService) {
        this.readerFileService = readerFileService;
    }


    @Override
    public void task() {

        readerFileService.reader(uploadedFolder);

    }
}
