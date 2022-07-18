package eu.gordaniil.rikexercisebe.domain.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Transactional(readOnly = true)
    @Override
    public EventDto getBy(String extId) {
        return null;
    }

    @Override
    public EventDto save(EventDto dto) {
        return null;
    }

    @Override
    public EventDto edit(EventDto dto) {
        return null;
    }

    @Override
    public void delete(String extId) {

    }

}
