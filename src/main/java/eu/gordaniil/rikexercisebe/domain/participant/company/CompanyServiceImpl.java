package eu.gordaniil.rikexercisebe.domain.participant.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    @Transactional(readOnly = true)
    @Override
    public CompanyDto getBy(String extId) {
        return null;
    }

    @Override
    public CompanyDto save(CompanyDto dto) {
        return null;
    }

    @Override
    public CompanyDto edit(CompanyDto dto) {
        return null;
    }

    @Override
    public void delete(String extId) {

    }

}
