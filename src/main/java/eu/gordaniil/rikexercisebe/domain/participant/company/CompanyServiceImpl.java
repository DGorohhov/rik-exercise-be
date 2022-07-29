package eu.gordaniil.rikexercisebe.domain.participant.company;

import eu.gordaniil.rikexercisebe.constant.ErrorMessages;
import eu.gordaniil.rikexercisebe.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper mapper;
    private final CompanyRepository repository;

    @Transactional(readOnly = true)
    @Override
    public CompanyDto getBy(String extId) {
        log.debug("DB: Fetching company participant by external ID: {}", extId);

        return repository.findByExtId(extId)
                .map(mapper::toDto)
                .orElseThrow(() -> {
                    log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "company participant", "extId", extId));

                    return new NotFoundException(
                            String.format(ErrorMessages.NOT_FOUND_MSG, "company participant", "extId", extId)
                    );
                });
    }

    @Override
    public CompanyDto save(CompanyDto dto) {
        log.debug("DB: Saving company participant: {}", dto);

        var dao = mapper.toDao(dto);
        var savedDao = repository.save(dao);

        return mapper.toDto(savedDao);
    }

    @Override
    public CompanyDto edit(CompanyDto dto) {
        log.debug("DB: Updating company participant: {}", dto);

        var existingDao = repository.findByExtId(dto.getExtId())
                .orElseThrow(() -> {
                    log.error("Company participant with external ID {} doesn't exist", dto.getExtId());

                    return new NotFoundException(String.format(ErrorMessages.NOT_FOUND_MSG, "company participant", "extId", dto.getExtId()));
                });

        mapper.toExistingCompany(existingDao, dto);
        var savedDao = repository.save(existingDao);

        return mapper.toDto(savedDao);
    }

    @Override
    public void delete(String extId) {
        log.debug("DB: Deleting company participant by external ID: {}", extId);

        repository.findByExtId(extId)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            log.error("Company participant with external ID {} doesn't exist", extId);
                            throw new NotFoundException(String.format(ErrorMessages.NOT_FOUND_MSG, "company participant", "extId", extId));
                        }
                );
    }

}
