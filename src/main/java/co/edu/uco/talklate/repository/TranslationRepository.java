package co.edu.uco.talklate.repository;

import co.edu.uco.talklate.repository.entities.TranslationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TranslationRepository extends CrudRepository<TranslationEntity, UUID> {

    Optional<TranslationEntity> findById(UUID id);
    ArrayList<TranslationEntity> findAll();

}
