package eu.gordaniil.rikexercisebe.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventDao, String> {

    Optional<EventDao> findByExtId(String extId);

}
