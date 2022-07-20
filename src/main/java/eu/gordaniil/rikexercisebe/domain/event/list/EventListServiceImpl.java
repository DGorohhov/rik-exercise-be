package eu.gordaniil.rikexercisebe.domain.event.list;

import eu.gordaniil.rikexercisebe.domain.PaginatedListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EventListServiceImpl implements EventListService {

    private final EventPreviewMapper mapper;
    private final EventPreviewRepository repository;

    @Override
    public PaginatedListResponse<EventPreviewVm> getAll(Integer page, Integer limit) {
        log.debug("DB: Retrieving all events");

        var result = repository.findAll(PageRequest.of(page, limit));
        var items = result.getContent().stream()
                .map(mapper::toVm)
                .collect(Collectors.toSet());

        return new PaginatedListResponse<>(items, result.getTotalElements());
    }

}
