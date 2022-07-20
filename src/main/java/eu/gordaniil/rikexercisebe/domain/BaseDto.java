package eu.gordaniil.rikexercisebe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.gordaniil.rikexercisebe.validation.ValidationConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {

    @JsonIgnore
    private Integer id;

    @Pattern(regexp = ValidationConstraint.UUID_V4_REGEXP, message = "'extId' must be a valid v4 UUID")
    private String extId;

    private LocalDateTime created;

    @JsonIgnore
    private String createdBy;

    private LocalDateTime updated;

    @JsonIgnore
    private String updatedBy;

}
