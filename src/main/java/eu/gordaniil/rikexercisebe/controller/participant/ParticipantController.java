package eu.gordaniil.rikexercisebe.controller.participant;

import eu.gordaniil.rikexercisebe.controller.BaseController;
import eu.gordaniil.rikexercisebe.domain.participant.ParticipantDto;
import eu.gordaniil.rikexercisebe.domain.participant.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/v1/participants")
public class ParticipantController implements BaseController<ParticipantDto> {

    private final ParticipantService service;

    @GetMapping("/{extId}")
    @Override
    public ParticipantDto getBy(@PathVariable String extId) {
        return service.getBy(extId);
    }

    @PostMapping
    @Override
    public ParticipantDto create(@Validated @RequestBody ParticipantDto dto) {
        return service.save(dto);
    }

    @PutMapping
    @Override
    public ParticipantDto edit(@Validated @RequestBody ParticipantDto dto) {
        return service.edit(dto);
    }

    @DeleteMapping("/{extId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable String extId) {
        service.delete(extId);
    }

}
