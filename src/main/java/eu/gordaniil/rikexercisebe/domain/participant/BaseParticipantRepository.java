package eu.gordaniil.rikexercisebe.domain.participant;

import eu.gordaniil.rikexercisebe.domain.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseParticipantRepository<T extends BaseDao> extends JpaRepository<T, String> {

    Optional<T> findByExtId(String extId);

}
