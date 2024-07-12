package br.com.luizalabs.application.scheduleds;

import br.com.luizalabs.application.services.ProcessFileService;
import br.com.luizalabs.application.services.ReaderFileService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledProcessFiles extends ScheduledTasks {

    private final ReaderFileService readerFileService;
    private final ProcessFileService processFileService;

    public ScheduledProcessFiles(ReaderFileService readerFileService, ProcessFileService processFileService) {
        this.readerFileService = readerFileService;
        this.processFileService = processFileService;
    }


    @Override
    public void task() {
        processFileService.process();
    }
}
