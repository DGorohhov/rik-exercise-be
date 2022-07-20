package eu.gordaniil.rikexercisebe.domain.event;

import eu.gordaniil.rikexercisebe.constant.ErrorMessages;
import eu.gordaniil.rikexercisebe.domain.participant.ParticipantTypes;
import eu.gordaniil.rikexercisebe.domain.participant.civilian.CivilianRepository;
import eu.gordaniil.rikexercisebe.domain.participant.company.CompanyRepository;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventParticipantPreviewVm;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventVm;
import eu.gordaniil.rikexercisebe.domain.participant.dto.EventParticipantDto;
import eu.gordaniil.rikexercisebe.error.GenericException;
import eu.gordaniil.rikexercisebe.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper mapper;
    private final EventRepository repository;
    private final CompanyRepository companyRepository;
    private final CivilianRepository civilianRepository;

    @Transactional(readOnly = true)
    @Override
    public EventDto getBy(String extId) {
        log.debug("DB: Fetching event by external ID: {}", extId);

        var existingEvent = getEventEntity(extId);

        return mapper.toDto(existingEvent);
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

    @Override
    public EventVm addParticipant(String eventId, EventParticipantDto dto) {
        log.debug("DB: Adding participant: {} to event with external ID: {}", dto, eventId);
        var existingEvent = getEventEntity(eventId);

        switch (dto.getType()) {
            case COMPANY -> {
                var existingCompany = companyRepository.findByExtId(dto.getId())
                        .orElseThrow(() -> {
                            log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "existingCompany", "extId", dto.getId()));

                            return new NotFoundException(
                                    String.format(ErrorMessages.NOT_FOUND_MSG, "existingCompany", "extId", dto.getId())
                            );
                        });
                existingEvent.getCompanyParticipants().add(existingCompany);
            }
            case CIVILIAN -> {
                var existingCivilian = civilianRepository.findByExtId(dto.getId())
                        .orElseThrow(() -> {
                            log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "existingCivilian", "extId", dto.getId()));

                            return new NotFoundException(
                                    String.format(ErrorMessages.NOT_FOUND_MSG, "existingCivilian", "extId", dto.getId())
                            );
                        });
                existingEvent.getCivilianParticipants().add(existingCivilian);
            }
            default -> {
                log.error("Participant type '{}' does not exist", dto.getType());
                throw new GenericException(
                        "Could not add participant",
                        String.format("Participant type '%s' does not exist", dto.getType())
                );
            }
        }

        var savedEvent = repository.save(existingEvent);

        return getEventVm(savedEvent);
    }

    @Override
    public EventVm removeParticipant(String eventId, EventParticipantDto dto) {
        log.debug("DB: Removing participant: {} from event with external ID: {}", dto, eventId);

        var existingEvent = getEventEntity(eventId);

        switch (dto.getType()) {
            case COMPANY -> {
                var existingCompany = companyRepository.findByExtId(dto.getId())
                        .orElseThrow(() -> {
                            log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "existingCompany", "extId", dto.getId()));

                            return new NotFoundException(
                                    String.format(ErrorMessages.NOT_FOUND_MSG, "existingCompany", "extId", dto.getId())
                            );
                        });
                existingEvent.getCompanyParticipants().remove(existingCompany);
            }
            case CIVILIAN -> {
                var existingCivilian = civilianRepository.findByExtId(dto.getId())
                        .orElseThrow(() -> {
                            log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "existingCivilian", "extId", dto.getId()));

                            return new NotFoundException(
                                    String.format(ErrorMessages.NOT_FOUND_MSG, "existingCivilian", "extId", dto.getId())
                            );
                        });
                existingEvent.getCivilianParticipants().remove(existingCivilian);
            }
            default -> {
                log.error("Participant type '{}' does not exist", dto.getType());
                throw new GenericException(
                        "Could not add participant",
                        String.format("Participant type '%s' does not exist", dto.getType())
                );
            }
        }

        var savedEvent = repository.save(existingEvent);

        return getEventVm(savedEvent);
    }

    @Override
    public EventVm getEventInfo(String eventId) {
        log.debug("Retrieving event info by external id: {}", eventId);

        var eventEntity = getEventEntity(eventId);

        return getEventVm(eventEntity);
    }

    private EventDao getEventEntity(String extId) {
        return repository.findByExtId(extId)
                .orElseThrow(() -> {
                    log.error(String.format(ErrorMessages.NOT_FOUND_MSG, "event", "extId", extId));

                    return new NotFoundException(
                            String.format(ErrorMessages.NOT_FOUND_MSG, "event", "extId", extId)
                    );
                });
    }

    private EventVm getEventVm(EventDao dao) {
        var eventVm = mapper.toVm(dao);
        eventVm.setParticipants(new ArrayList<>());
        dao.getCompanyParticipants()
                .forEach(
                        c -> eventVm.getParticipants().add(
                                EventParticipantPreviewVm.builder()
                                        .id(c.getId())
                                        .extId(c.getExtId())
                                        .type(ParticipantTypes.COMPANY)
                                        .name(c.getLegalName())
                                        .personalIdentificationNumber(c.getRegisterCode())
                                        .build()
                        )
                );

        dao.getCivilianParticipants()
                .forEach(
                        c -> eventVm.getParticipants().add(
                                EventParticipantPreviewVm.builder()
                                        .id(c.getId())
                                        .extId(c.getExtId())
                                        .type(ParticipantTypes.CIVILIAN)
                                        .name(c.getFirstName() + " " + c.getLastName())
                                        .personalIdentificationNumber(c.getPersonalIdentityNumber())
                                        .build()
                        )
                );

        // TODO: sort participants by the ID???

        return eventVm;
    }

}
