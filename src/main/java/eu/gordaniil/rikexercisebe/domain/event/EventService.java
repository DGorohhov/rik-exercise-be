package eu.gordaniil.rikexercisebe.domain.event;

import eu.gordaniil.rikexercisebe.domain.BaseServiceInterface;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventVm;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventParticipantDto;

public interface EventService extends BaseServiceInterface<EventDto> {

    EventVm addParticipant(String eventId, EventParticipantDto dto);

    EventVm removeParticipant(String eventId, EventParticipantDto dto);

    EventVm getEventInfo(String eventId);

}
