package eu.gordaniil.rikexercisebe.domain.event;

import eu.gordaniil.rikexercisebe.BaseTest;
import eu.gordaniil.rikexercisebe.domain.participant.ParticipantTypes;
import eu.gordaniil.rikexercisebe.domain.participant.civilian.CivilianRepository;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventParticipantDto;
import eu.gordaniil.rikexercisebe.error.NotFoundException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EventServiceTest extends BaseTest {

    @Mock
    private CivilianRepository civilianRepository;

    @Mock
    private EventRepository repository;

    @Spy
    private EventMapperImpl mapper;

    @InjectMocks
    private EventServiceImpl service;

    @Test
    public void givenValidEventId_whenSearchingForEventData_thenShouldReturnEventDto() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntity));

        var result = service.getBy("3f7c41d0-7a69-4f5f-8107-cc9d0183198c");
        assertNotNull(result);
    }

    @Test
    public void givenValidEventDto_whenSavingEventEntity_thenShouldSuccessfullySaveAndReturnDto() {
        var dto = EventHelper.eventDto;
        when(repository.save(any())).thenReturn(EventHelper.eventEntity);

        var result = service.save(dto);
        assertNotNull(result);
        assertEquals(result.getName(), dto.getName());
    }

    @Test
    public void givenValidEventDto_whenUpdatingEventEntity_thenShouldSuccessfullyUpdateAndReturnDto() {
        var dto = EventHelper.eventDto;
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntity));
        when(repository.save(any())).thenReturn(EventHelper.eventEntity);

        var result = service.edit(dto);
        assertNotNull(result);
        assertEquals(result.getName(), dto.getName());
    }

    @Test(expected = NotFoundException.class)
    public void givenNonExistingEventDto_whenSearchingForEventEntity_thenShouldFailAndThrowNotFoundException() {
        when(repository.findByExtId(any())).thenReturn(Optional.empty());

        service.edit(EventHelper.eventDto);
    }

    @Test
    public void givenExistingEventId_whenTryingToDeleteEventEntity_thenShouldSuccessfullyDelete() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntity()));

        service.delete(EventHelper.eventDto.getExtId());
    }

    @Test(expected = NotFoundException.class)
    public void givenNonExistingEventId_whenSearchingForEventEntity_thenShouldFailAndThrowNotFoundException() {
        when(repository.findByExtId(any())).thenReturn(Optional.empty());

        service.delete(EventHelper.eventDto.getExtId());
    }

    @Test
    public void givenExistingEventIdAndParticipant_whenTryingToAddParticipant_thenShouldSuccessfullyAddAndReturnEventVm() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntityWithEmptyParticipantLists()));
        when(civilianRepository.findByExtId(any())).thenReturn(Optional.of(EventHelper.civilianEntity()));
        when(repository.save(any())).thenReturn(EventHelper.eventEntityWithEmptyParticipantLists());

        var result = service.addParticipant(EventHelper.eventDto.getExtId(), new EventParticipantDto("f0a01bdf-1217-447f-8f95-562947aa58c9", ParticipantTypes.CIVILIAN));
        assertTrue(Objects.nonNull(result));
    }

    @Test(expected = NotFoundException.class)
    public void givenExistingEventIdAndNonExistingParticipant_whenTryingToFindParticipant_thenShouldFailAndThrowNotFoundException() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntityWithEmptyParticipantLists()));
        when(civilianRepository.findByExtId(any())).thenReturn(Optional.empty());

        var result = service.addParticipant(EventHelper.eventDto.getExtId(), new EventParticipantDto("f0a01bdf-1217-447f-8f95-562947aa58c9", ParticipantTypes.CIVILIAN));
        assertTrue(Objects.nonNull(result));
    }

    @Test
    public void givenExistingEventIdAndParticipant_whenTryingToRemoveParticipant_thenShouldSuccessfullyRemoveAndReturnEventVm() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntityWithEmptyParticipantLists()));
        when(civilianRepository.findByExtId(any())).thenReturn(Optional.of(EventHelper.civilianEntity()));
        when(repository.save(any())).thenReturn(EventHelper.eventEntityWithEmptyParticipantLists());

        var result = service.removeParticipant(EventHelper.eventDto.getExtId(), new EventParticipantDto("f0a01bdf-1217-447f-8f95-562947aa58c9", ParticipantTypes.CIVILIAN));
        assertTrue(Objects.nonNull(result));
    }

    @Test(expected = NotFoundException.class)
    public void givenExistingEventIdAndNonExistingParticipant_whenTryingToFindParticipantForRemoval_thenShouldFailAndThrowNotFoundException() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(EventHelper.eventEntityWithEmptyParticipantLists()));
        when(civilianRepository.findByExtId(any())).thenReturn(Optional.empty());

        var result = service.addParticipant(EventHelper.eventDto.getExtId(), new EventParticipantDto("f0a01bdf-1217-447f-8f95-562947aa58c9", ParticipantTypes.CIVILIAN));
        assertTrue(Objects.nonNull(result));
    }

}
