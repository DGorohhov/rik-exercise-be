package eu.gordaniil.rikexercisebe.domain.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.gordaniil.rikexercisebe.domain.participant.ParticipantTypes;
import eu.gordaniil.rikexercisebe.domain.participant.civilian.CivilianDao;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventParticipantPreviewVm;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventVm;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

@UtilityClass
public class EventHelper {

    private final LocalDateTime timeNow = LocalDateTime.now();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public final EventDao eventEntity = new EventDao(
            "Test event",
            timeNow,
            "Tallinn",
            "Info",
            null,
            null
    );

    public final EventDto eventDto = EventDto.builder()
            .extId("3f7c41d0-7a69-4f5f-8107-cc9d0183198c")
            .name("Test event")
            .date(timeNow)
            .location("Tallinn")
            .additionalInfo("Info")
            .build();

    public EventDao eventEntity() {
        var entity = new EventDao(
                "Test event",
                timeNow,
                "Tallinn",
                "Info",
                null,
                null
        );
        entity.setExtId("3f7c41d0-7a69-4f5f-8107-cc9d0183198c");

        return entity;
    }

    public EventDao eventEntityWithEmptyParticipantLists() {
        var entity = new EventDao(
                "Test event",
                timeNow,
                "Tallinn",
                "Info",
                new HashSet<>(),
                new HashSet<>()
        );
        entity.setExtId("3f7c41d0-7a69-4f5f-8107-cc9d0183198c");

        return entity;
    }

    public CivilianDao civilianEntity() {
        var entity = new CivilianDao(
                "Daniil",
                "Gor",
                "12345678901",
                "CASH",
                "Info",
                null
        );
        entity.setExtId("3f7c41d0-7a69-4f5f-8107-cc9d0183198c");

        return entity;
    }

    public EventVm eventViewModel() {
        return EventVm.builder()
                .name("Test event")
                .date(timeNow)
                .location("Tallinn")
                .additionalInfo("Info")
                .isEndedEvent(Boolean.FALSE)
                .participants(
                        Collections.singletonList(
                                EventParticipantPreviewVm.builder()
                                        .id(2)
                                        .extId("3f7c41d0-7a69-4f5f-8107-cc9d0183198c")
                                        .name("Daniil")
                                        .personalIdentificationNumber("12345678901")
                                        .type(ParticipantTypes.CIVILIAN)
                                        .build()
                        )
                )
                .build();
    }

    public final EventDto noTimeEventDto = EventDto.builder()
            .extId("3f7c41d0-7a69-4f5f-8107-cc9d0183198c")
            .name("Test event")
            .location("Tallinn")
            .additionalInfo("Info")
            .build();

    public String eventDtoJsonString() {
        try {
            return objectMapper.writeValueAsString(noTimeEventDto);
        } catch (JsonProcessingException e) {
            System.out.println("Error writing object as string: " +  e.getMessage());
        }

        return null;
    }

}
