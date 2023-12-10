package com.adldoost.caracallanguage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(
        chain = true
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Word_Source_Version_Info")
public class WordSourceVersionInfo implements Serializable {

    @Id
    private String id;
    private String sourceId;
    private Integer version;
    private LocalDate versionDate;
    private String description;
}
