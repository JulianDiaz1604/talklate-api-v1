package co.edu.uco.talklate.service;

import co.edu.uco.talklate.domain.translation.TranslationCreateRequest;
import co.edu.uco.talklate.domain.translation.TranslationResponse;
import co.edu.uco.talklate.domain.translation.TranslationUpdateRequest;

import java.util.ArrayList;
import java.util.UUID;

public interface TranslationService {

    TranslationResponse createTranslation(TranslationCreateRequest translationCreateRequest);
    ArrayList<TranslationResponse> listAllTranslations();
    TranslationResponse updateTranslation(TranslationUpdateRequest translationRequest);
    boolean deleteTranslation(UUID uuid);
    TranslationResponse getTranslationById(UUID uuid);

}
