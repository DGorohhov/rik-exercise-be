package eu.gordaniil.rikexercisebe.domain.participant.civilian;

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
public class CivilianServiceImpl implements CivilianService {

    private final CivilianMapper mapper;
    private final CivilianRepository repository;

    @Transactional(readOnly = true)
    @Override
    public CivilianDto getBy(String extId) {
        log.debug("DB: Fetching civilian participant by external ID: {}", extId);

        return repository.findByExtId(extId)
                .map(mapper::toDto)
                .orElseThrow(() -> {
                    log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "civilian participant", "extId", extId));

                    return new NotFoundException(
                            String.format(ErrorMessages.NOT_FOUND_MSG, "civilian participant", "extId", extId)
                    );
                });
    }

    @Override
    public CivilianDto save(CivilianDto dto) {
        log.debug("DB: Saving civilian participant: {}", dto);

        var dao = mapper.toDao(dto);
        var savedDao = repository.save(dao);

        return mapper.toDto(savedDao);
    }

    @Override
    public CivilianDto edit(CivilianDto dto) {
        log.debug("DB: Updating civilian participant: {}", dto);

        var existingDao = repository.findByExtId(dto.getExtId())
                .orElseThrow(() -> {
                    log.error("Civilian participant with external ID {} doesn't exist", dto.getExtId());

                    return new NotFoundException(String.format(ErrorMessages.NOT_FOUND_MSG, "civilian participant", "extId", dto.getExtId()));
                });

        mapper.toExistingCivilian(existingDao, dto);
        var savedDao = repository.save(existingDao);

        return mapper.toDto(savedDao);
    }

    @Override
    public void delete(String extId) {
        log.debug("DB: Deleting civilian participant by external ID: {}", extId);

        repository.findByExtId(extId)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            log.error("Civilian participant with external ID {} doesn't exist", extId);
                            throw new NotFoundException(String.format(ErrorMessages.NOT_FOUND_MSG, "civilian participant", "extId", extId));
                        }
                );
    }

}
