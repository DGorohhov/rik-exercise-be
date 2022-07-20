package eu.gordaniil.rikexercisebe.domain.event.list;

import eu.gordaniil.rikexercisebe.domain.PaginatedListResponse;

public interface EventListService {

    PaginatedListResponse<EventPreviewVm> getAll(Integer page, Integer limit);

}
