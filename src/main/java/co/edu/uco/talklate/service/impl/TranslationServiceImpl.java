package co.edu.uco.talklate.service.impl;

import co.edu.uco.talklate.domain.translation.TranslationRequest;
import co.edu.uco.talklate.domain.translation.TranslationResponse;
import co.edu.uco.talklate.repository.TranslationRepository;
import co.edu.uco.talklate.repository.entities.TranslationEntity;
import co.edu.uco.talklate.service.TranslationService;
import co.edu.uco.talklate.util.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;
    private final GenericMapper mapper;

    @Override
    public TranslationResponse createTranslation(TranslationRequest translationRequest) {
        TranslationEntity translationEntity = mapper.map(translationRequest, TranslationEntity.class);
        translationEntity.setId(UUID.randomUUID());
        return mapper.map(translationRepository.save(translationEntity), TranslationResponse.class);
    }

    @Override
    public ArrayList<TranslationResponse> listAllTranslations() {
        return mapper.mapList(translationRepository.findAll(), TranslationResponse.class);
    }

    @Override
    public TranslationResponse updateTranslation(TranslationRequest translationRequest) {
        TranslationEntity translationEntity = mapper.map(translationRequest, TranslationEntity.class);
        return mapper.map(translationRepository.save(translationEntity), TranslationResponse.class);
    }

    @Override
    public boolean deleteTranslation(UUID id) {
        try {
            translationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
