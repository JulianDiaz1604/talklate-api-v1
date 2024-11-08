package co.edu.uco.talklate.service.impl;

import co.edu.uco.talklate.domain.translation.TranslationRequest;
import co.edu.uco.talklate.domain.translation.TranslationResponse;
import co.edu.uco.talklate.repository.TranslationRepository;
import co.edu.uco.talklate.repository.entities.TranslationEntity;
import co.edu.uco.talklate.service.TranslationService;
import co.edu.uco.talklate.util.mapper.GenericMapper;
import co.edu.uco.talklate.util.validator.NullValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;
    private final GenericMapper mapper;

    @Override
    public TranslationResponse createTranslation(TranslationRequest translationRequest) {
        NullValidator<TranslationRequest> validator = new NullValidator<>();
        try {
            validator.validateFields(translationRequest);
            TranslationEntity translationEntity = mapper.map(translationRequest, TranslationEntity.class);
            translationEntity.setId(UUID.randomUUID());
            return mapper.map(translationRepository.save(translationEntity), TranslationResponse.class);
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException("Invalid translation request: " + e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during translation request create: {}", e.getMessage());
            throw new RuntimeException("Translation could not be created due to an internal error");
        }
    }

    @Override
    public ArrayList<TranslationResponse> listAllTranslations() {
        try {
            return mapper.mapList(translationRepository.findAll(), TranslationResponse.class);
        } catch (Exception e) {
            log.error("Failed request: {}", e.getMessage());
            throw new RuntimeException("Failed request: " + e.getMessage());
        }
    }

    @Override
    public TranslationResponse updateTranslation(TranslationRequest translationRequest) {
        NullValidator<TranslationRequest> validator = new NullValidator<>();
        try {
            validator.validateFields(translationRequest);
            TranslationEntity translationEntity = mapper.map(translationRequest, TranslationEntity.class);
            return mapper.map(translationRepository.save(translationEntity), TranslationResponse.class);
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException("Invalid translation request: " + e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during translation request update: {}", e.getMessage());
            throw new RuntimeException("Translation could not be updated due to an internal error");
        }
    }

    @Override
    public boolean deleteTranslation(UUID id) {
        try {
            if (!translationRepository.existsById(id)) {
                throw new IllegalArgumentException("Translation with id " + id + " does not exist.");
            }
            translationRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("An error occurred while trying to delete translation with id " + id + ": " + e.getMessage(), e);
            return false;
        }
    }

}
