package eu.gordaniil.rikexercisebe.domain.event.list;

import eu.gordaniil.rikexercisebe.domain.PaginatedListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EventListServiceImpl implements EventListService {

    private final EventPreviewMapper mapper;
    private final EventPreviewRepository repository;

    @Override
    public PaginatedListResponse<EventPreviewVm> getUpcoming(Integer page, Integer limit) {
        log.debug("DB: Retrieving all upcoming events");

        var result = repository.findAllByDateAfter(PageRequest.of(page, limit), LocalDateTime.now());
        var items = result.getContent().stream()
                .map(mapper::toVm)
                .collect(Collectors.toSet());

        return new PaginatedListResponse<>(items, result.getTotalElements());
    }

    @Override
    public PaginatedListResponse<EventPreviewVm> getEnded(Integer page, Integer limit) {
        log.debug("DB: Retrieving all ended events");

        var result = repository.findAllByDateBefore(PageRequest.of(page, limit), LocalDateTime.now());
        var items = result.getContent().stream()
                .map(mapper::toVm)
                .collect(Collectors.toSet());

        return new PaginatedListResponse<>(items, result.getTotalElements());
    }

}
