package eu.gordaniil.rikexercisebe.domain.event.list;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventPreviewRepository extends JpaRepository<EventPreviewDao, String> {

    Page<EventPreviewDao> findAllByDateAfter(Pageable pageable, LocalDateTime date);

    Page<EventPreviewDao> findAllByDateBefore(Pageable pageable, LocalDateTime date);

}
