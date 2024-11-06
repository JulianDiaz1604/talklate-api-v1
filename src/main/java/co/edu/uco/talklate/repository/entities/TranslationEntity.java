package co.edu.uco.talklate.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "translations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationEntity {

    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "description")
    String description;

    @Column(name = "creator")
    String creator;

    @Column(name = "origin_language")
    String originLanguage;

    @Column(name = "target_language")
    String targetLanguage;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "finish_date")
    Date finishDate;

}
