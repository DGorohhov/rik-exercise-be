package eu.gordaniil.rikexercisebe.controller.participant.company;

import eu.gordaniil.rikexercisebe.controller.BaseController;
import eu.gordaniil.rikexercisebe.domain.participant.company.CompanyDto;
import eu.gordaniil.rikexercisebe.domain.participant.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/v1/participant/companies")
public class CompanyController implements BaseController<CompanyDto> {

    private final CompanyService service;

    @Override
    public CompanyDto getBy(@PathVariable String extId) {
        return service.getBy(extId);
    }

    @Override
    public CompanyDto create(@Validated @RequestBody CompanyDto dto) {
        return service.save(dto);
    }

    @Override
    public CompanyDto edit(@Validated @RequestBody CompanyDto dto) {
        return service.edit(dto);
    }

    @DeleteMapping("/{extId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable String extId) {
        service.delete(extId);
    }

}
