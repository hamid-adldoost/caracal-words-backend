package com.adldoost.caracallanguage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String mobile;
    private String email;
    private LocalDateTime signupDate;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String nationalId;
    private String imageUrl;
}
