package eu.gordaniil.rikexercisebe.domain.event;

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
public class EventServiceImpl implements EventService {

    private final EventMapper mapper;
    private final EventRepository repository;

    @Transactional(readOnly = true)
    @Override
    public EventDto getBy(String extId) {
        log.debug("DB: Fetching event by external ID: {}", extId);

        return repository.findByExtId(extId)
                .map(mapper::toDto)
                .orElseThrow(() -> {
                    log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "event", "extId", extId));

                    return new NotFoundException(
                            String.format(ErrorMessages.NOT_FOUND_MSG, "event", "extId", extId)
                    );
                });
    }

    @Override
    public EventDto save(EventDto dto) {
        log.debug("DB: Saving event: {}", dto);

        var dao = mapper.toDao(dto);
        var savedDao = repository.save(dao);

        return mapper.toDto(savedDao);
    }

    @Override
    public EventDto edit(EventDto dto) {
        log.debug("DB: Updating event: {}", dto);

        var existingDao = repository.findByExtId(dto.getExtId())
                .orElseThrow(() -> {
                    log.error("Event with external ID {} doesn't exist", dto.getExtId());

                    return new NotFoundException(String.format(ErrorMessages.NOT_FOUND_MSG, "event", "extId", dto.getExtId()));
                });

        mapper.toExistingDao(existingDao, dto);
        var savedDao = repository.save(existingDao);

        return mapper.toDto(savedDao);
    }

    @Override
    public void delete(String extId) {
        log.debug("DB: Deleting event by external ID: {}", extId);

        repository.findByExtId(extId)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            log.error("Event with external ID {} doesn't exist", extId);
                            throw new NotFoundException(String.format(ErrorMessages.NOT_FOUND_MSG, "event", "extId", extId));
                        }
                );
    }

}
