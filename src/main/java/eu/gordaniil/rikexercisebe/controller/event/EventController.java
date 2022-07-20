package eu.gordaniil.rikexercisebe.controller.event;

import eu.gordaniil.rikexercisebe.controller.BaseController;
import eu.gordaniil.rikexercisebe.domain.event.EventDto;
import eu.gordaniil.rikexercisebe.domain.event.EventService;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventVm;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventParticipantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/v1/events")
public class EventController implements BaseController<EventDto> {

    private final EventService service;

    @GetMapping("/{extId}")
    @Override
    public EventDto getBy(@PathVariable String extId) {
        return service.getBy(extId);
    }

    @GetMapping("/{extId}/info")
    public EventDto getInfoBy(@PathVariable String extId) {
        return service.getEventInfo(extId);
    }

    @PostMapping
    @Override
    public EventDto create(@Validated @RequestBody EventDto dto) {
        return service.save(dto);
    }

    @PostMapping("/{eventId}")
    public EventVm addParticipant(@PathVariable String eventId, @Validated @RequestBody EventParticipantDto dto) {
        return service.addParticipant(eventId, dto);
    }

    @PutMapping
    @Override
    public EventDto edit(@Validated @RequestBody EventDto dto) {
        return service.edit(dto);
    }

    @DeleteMapping("/{extId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable String extId) {
        service.delete(extId);
    }

    @DeleteMapping("/participants/{eventId}")
    public EventVm removeParticipant(@PathVariable String eventId, @Validated @RequestBody EventParticipantDto dto) {
        return service.removeParticipant(eventId, dto);
    }

}
