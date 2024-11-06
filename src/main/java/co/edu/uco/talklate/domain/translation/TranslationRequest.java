package co.edu.uco.talklate.domain.translation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequest {

    String description;
    String creator;
    String originLanguage;
    String targetLanguage;
    Date startDate;
    Date finishDate;

}
