package eu.gordaniil.rikexercisebe.controller.event;

import eu.gordaniil.rikexercisebe.domain.PaginatedListResponse;
import eu.gordaniil.rikexercisebe.domain.event.list.EventListService;
import eu.gordaniil.rikexercisebe.domain.event.list.EventPreviewVm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/v1/events")
public class EventListController {

    private final EventListService service;

    @GetMapping("/upcoming")
    public PaginatedListResponse<EventPreviewVm> getUpcoming(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        return service.getUpcoming(page, limit);
    }

    @GetMapping("/ended")
    public PaginatedListResponse<EventPreviewVm> getEnded(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        return service.getEnded(page, limit);
    }

}
