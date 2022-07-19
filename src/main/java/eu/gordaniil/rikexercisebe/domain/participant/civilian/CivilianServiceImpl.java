package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CivilianServiceImpl implements CivilianService {

    @Transactional(readOnly = true)
    @Override
    public CivilianDto getBy(String extId) {
        return null;
    }

    @Override
    public CivilianDto save(CivilianDto dto) {
        return null;
    }

    @Override
    public CivilianDto edit(CivilianDto dto) {
        return null;
    }

    @Override
    public void delete(String extId) {

    }

}
