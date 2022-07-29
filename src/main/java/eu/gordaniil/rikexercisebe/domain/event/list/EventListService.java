package eu.gordaniil.rikexercisebe.domain.event.list;

import eu.gordaniil.rikexercisebe.domain.PaginatedListResponse;

public interface EventListService {

    PaginatedListResponse<EventPreviewVm> getUpcoming(Integer page, Integer limit);

    PaginatedListResponse<EventPreviewVm> getEnded(Integer page, Integer limit);

}
