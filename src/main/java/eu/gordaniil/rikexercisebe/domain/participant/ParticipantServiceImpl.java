package eu.gordaniil.rikexercisebe.domain.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    @Transactional(readOnly = true)
    @Override
    public ParticipantDto getBy(String extId) {
        return null;
    }

    @Override
    public ParticipantDto save(ParticipantDto dto) {
        return null;
    }

    @Override
    public ParticipantDto edit(ParticipantDto dto) {
        return null;
    }

    @Override
    public void delete(String extId) {

    }

}
