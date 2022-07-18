package eu.gordaniil.rikexercisebe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {

    @JsonIgnore
    private Integer id;

    private String extId;

    private LocalDateTime created;

    @JsonIgnore
    private String createdBy;

    private LocalDateTime updated;

    @JsonIgnore
    private String updatedBy;

}
