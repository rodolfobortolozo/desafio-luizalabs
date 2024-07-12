package br.com.luizalabs.adapter.persistence.repository;

import br.com.luizalabs.adapter.persistence.models.FileData;
import br.com.luizalabs.domain.enums.StatusFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FileRepository extends AbstractRepository<FileData, UUID> {

    @Override
    public Class getEntity() {
        return FileData.class;
    }

    public List<FileData> getPendingContent(){
        return getEntityManager()
                .createQuery("FROM FileData f WHERE f.processed = :STATUS", FileData.class)
                .setParameter("STATUS",StatusFile.PENDING)
                .getResultList();
    }

    public void updateStatusContent(UUID id) {

        Optional<FileData> file = findById(id);
        if(file.isPresent()){
            file.get().setProcessed(StatusFile.PROCESSED);
            getEntityManager().merge(file.get());
        }

    }
}
