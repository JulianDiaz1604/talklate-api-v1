package co.edu.uco.talklate.controller;

import co.edu.uco.talklate.domain.translation.TranslationRequest;
import co.edu.uco.talklate.domain.translation.TranslationResponse;
import co.edu.uco.talklate.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping(value = "create")
    public ResponseEntity<TranslationResponse> createRequest(@RequestBody TranslationRequest translationRequest) {
        return ResponseEntity.ok(translationService.createTranslation(translationRequest));
    }

    @GetMapping(value = "all")
    public ResponseEntity<ArrayList<TranslationResponse>> getAllRequest() {
        return ResponseEntity.ok(translationService.listAllTranslations());
    }

    @PostMapping(value = "update")
    public ResponseEntity<TranslationResponse> updateRequest(@RequestBody TranslationRequest translationRequest) {
        return ResponseEntity.ok(translationService.updateTranslation(translationRequest));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<Boolean> deleteRequest(UUID id) {
        return ResponseEntity.ok(translationService.deleteTranslation(id));
    }

}
