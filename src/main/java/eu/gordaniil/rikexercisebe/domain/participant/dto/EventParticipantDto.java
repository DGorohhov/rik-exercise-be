package eu.gordaniil.rikexercisebe.domain.participant.dto;

import eu.gordaniil.rikexercisebe.domain.participant.ParticipantTypes;
import eu.gordaniil.rikexercisebe.validation.ValidationConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipantDto {

    @NotEmpty(message = "'id' cannot be null or empty")
    @Pattern(regexp = ValidationConstraint.UUID_V4_REGEXP, message = "'extId' must be a valid v4 UUID")
    private String id;

    @NotNull(message = "'type' cannot be null or empty")
    private ParticipantTypes type;

}
