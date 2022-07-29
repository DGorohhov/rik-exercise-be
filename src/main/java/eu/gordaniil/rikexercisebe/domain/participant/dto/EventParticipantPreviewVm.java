package eu.gordaniil.rikexercisebe.domain.participant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.gordaniil.rikexercisebe.domain.participant.ParticipantTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipantPreviewVm {

    @JsonIgnore
    private Integer id;

    private String extId;

    private ParticipantTypes type;

    private String name;

    private String personalIdentificationNumber;

}
