package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import eu.gordaniil.rikexercisebe.domain.BaseDao;
import eu.gordaniil.rikexercisebe.domain.event.EventDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "civilian",
        indexes = {
                @Index(name = "civilian_id_index", columnList = "id"),
                @Index(name = "civilian_ext_index", columnList = "ext_id"),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_civilian_ext_id", columnNames = {"ext_id"})
        }
)
public class CivilianDao extends BaseDao implements Serializable {

    @NotNull(message = "'first_name' cannot be empty or null")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "'last_name' cannot be empty or null")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "'personal_identity_number' cannot be empty or null")
    @Column(name = "personal_identity_number", length = 11)
    private String personalIdentityNumber;

    @NotNull(message = "'payment_type' cannot be empty or null")
    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "additional_info", length = 1500)
    private String additionalInfo;

    @ManyToMany(
            mappedBy = "civilianParticipants",
            fetch = FetchType.LAZY
    )
    private Set<EventDao> events;

    @PreRemove
    private void removeEventsFromCivilians() {
        for(var event : events) {
            event.getCivilianParticipants().remove(this);
        }
    }

}
