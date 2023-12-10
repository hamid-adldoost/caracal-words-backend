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
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "User_Word_Source")
public class UserWordSource implements Serializable {

    @Id
    private String id;
    private String user;
    private LocalDateTime creationDate;
    private String wordSourceId;
    private String sourceTitle;
    private String sourceDescription;
    private List<UserWord> userWords;

}
