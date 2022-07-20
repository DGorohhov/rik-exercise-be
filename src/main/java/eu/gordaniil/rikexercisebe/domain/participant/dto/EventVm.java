package eu.gordaniil.rikexercisebe.domain.participant.dto;

import eu.gordaniil.rikexercisebe.domain.event.EventDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EventVm extends EventDto {

    private List<EventParticipantPreviewVm> participants;

}
