package eu.gordaniil.rikexercisebe.domain.participant.company;

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
        name = "company",
        indexes = {
                @Index(name = "company_id_index", columnList = "id"),
                @Index(name = "company_ext_index", columnList = "ext_id"),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_company_ext_id", columnNames = {"ext_id"})
        }
)
public class CompanyDao extends BaseDao implements Serializable {

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "register_code")
    private String registerCode;

    @Column(name = "number_of_participants")
    private Integer numberOfParticipants;

    @NotNull(message = "'payment_type' cannot be empty or null")
    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "additional_info", length = 5000)
    private String additionalInfo;

    @ManyToMany(
            mappedBy = "companyParticipants",
            fetch = FetchType.LAZY
    )
    private Set<EventDao> events;

    @PreRemove
    private void removeEventsFromCompanies() {
        for(var event : events) {
            event.getCompanyParticipants().remove(this);
        }
    }

}
