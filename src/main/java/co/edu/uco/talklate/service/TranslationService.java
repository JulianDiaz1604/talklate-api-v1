package co.edu.uco.talklate.service;

import co.edu.uco.talklate.domain.translation.TranslationRequest;
import co.edu.uco.talklate.domain.translation.TranslationResponse;

import java.util.ArrayList;
import java.util.UUID;

public interface TranslationService {

    TranslationResponse createTranslation(TranslationRequest translationRequest);
    ArrayList<TranslationResponse> listAllTranslations();
    TranslationResponse updateTranslation(TranslationRequest translationRequest);
    boolean deleteTranslation(UUID uuid);

}
