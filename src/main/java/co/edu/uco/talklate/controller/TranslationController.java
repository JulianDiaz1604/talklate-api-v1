package co.edu.uco.talklate.controller;

import co.edu.uco.talklate.domain.translation.TranslationRequest;
import co.edu.uco.talklate.domain.translation.TranslationResponse;
import co.edu.uco.talklate.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createRequest(@RequestBody TranslationRequest translationRequest) {
        try {
            TranslationResponse translationResponse = translationService.createTranslation(translationRequest);
            if (translationResponse != null) {
                return ResponseEntity.ok(translationResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating request");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllRequest() {
        try {
            ArrayList<TranslationResponse> translationResponse = translationService.listAllTranslations();
            if (translationResponse != null) {
                return ResponseEntity.ok(translationResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error getting request list");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "update")
    public ResponseEntity<?> updateRequest(@RequestBody TranslationRequest translationRequest) {
        try {
            TranslationResponse translationResponse = translationService.updateTranslation(translationRequest);
            if (translationResponse != null) {
                return ResponseEntity.ok(translationResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating request");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable UUID id) {
        try {
            Boolean response = translationService.deleteTranslation(id);
            if (response) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting request");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
