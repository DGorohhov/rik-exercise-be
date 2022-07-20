package eu.gordaniil.rikexercisebe.domain.event;

import eu.gordaniil.rikexercisebe.domain.BaseDao;
import eu.gordaniil.rikexercisebe.domain.participant.civilian.CivilianDao;
import eu.gordaniil.rikexercisebe.domain.participant.company.CompanyDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
public class EventDao extends BaseDao implements Serializable {

    @NotNull(message = "'name' cannot be empty or null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "'date' cannot be empty or null")
    @Column(name = "event_date")
    private LocalDateTime date;

    @NotNull(message = "'location' cannot be empty or null")
    @Column(name = "location")
    private String location;

    @Column(name = "additional_info", length = 1000)
    private String additionalInfo;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "event_civilian_join",
            joinColumns = {
                @JoinColumn(name = "event_id", referencedColumnName = "ext_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "civilian_id", referencedColumnName = "ext_id")
            }
    )
    private Set<CivilianDao> civilianParticipants = new HashSet<CivilianDao>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "event_company_join",
            joinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "ext_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "company_id", referencedColumnName = "ext_id")
            }
    )
    private Set<CompanyDao> companyParticipants = new HashSet<CompanyDao>();

}
