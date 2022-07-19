package eu.gordaniil.rikexercisebe.controller.participant.civilian;

import eu.gordaniil.rikexercisebe.controller.BaseController;
import eu.gordaniil.rikexercisebe.domain.participant.civilian.CivilianDto;
import eu.gordaniil.rikexercisebe.domain.participant.civilian.CivilianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/v1/participant/civilians")
public class CivilianController implements BaseController<CivilianDto> {

    private final CivilianService service;

    @GetMapping("/{extId}")
    @Override
    public CivilianDto getBy(@PathVariable String extId) {
        return service.getBy(extId);
    }

    @PostMapping
    @Override
    public CivilianDto create(@Validated @RequestBody CivilianDto dto) {
        return service.save(dto);
    }

    @PutMapping
    @Override
    public CivilianDto edit(@Validated @RequestBody CivilianDto dto) {
        return service.edit(dto);
    }

    @DeleteMapping("/{extId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable String extId) {
        service.delete(extId);
    }

}
