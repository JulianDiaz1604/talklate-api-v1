package co.edu.uco.talklate.domain.translation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationUpdateRequest {

    UUID id;
    String description;
    String creator;
    String translator;
    String originLanguage;
    String targetLanguage;
    Date startDate;
    Date finishDate;

}
