package eu.gordaniil.rikexercisebe.domain.event.list;

import eu.gordaniil.rikexercisebe.domain.BaseDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "event",
        indexes = {
                @Index(name = "event_id_index", columnList = "id"),
                @Index(name = "event_ext_index", columnList = "ext_id"),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_event_ext_id", columnNames = {"ext_id"})
        }
)
public class EventPreviewDao extends BaseDao implements Serializable {

    @NotNull(message = "'name' cannot be empty or null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "'date' cannot be empty or null")
    @Column(name = "event_date")
    private LocalDateTime date;

}
