package br.com.luizalabs.domain;

import br.com.luizalabs.domain.enums.StatusFile;
import br.com.luizalabs.adapter.persistence.models.IEntidade;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class File implements IEntidade<UUID> {

    private UUID id;
    private String fileName;
    private String content;
    private Long lineNumber;
    private LocalDate dateInsert;
    private StatusFile processed;

}
