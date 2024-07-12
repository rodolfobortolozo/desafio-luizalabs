package br.com.luizalabs.adapter.persistence.models;

import br.com.luizalabs.domain.enums.StatusFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "TAB001_FILE")
@NoArgsConstructor
@AllArgsConstructor
public class FileData implements IEntidade<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fileName;
    private String content;
    private Long lineNumber;
    private LocalDate dateInsert;
    @Enumerated(EnumType.STRING)
    private StatusFile processed;

}
