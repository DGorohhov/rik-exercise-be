package eu.gordaniil.rikexercisebe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

import static eu.gordaniil.rikexercisebe.helper.UserHelper.getUserFromContext;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseDao {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Size(max = 36)
    @Column(name = "ext_id", nullable = false, updatable = false)
    private String extId;

    @NotNull
    @Column(name = "created")
    private LocalDateTime created;

    @NotEmpty
    @Size(max = 200)
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void preCreate() {
        var user = getUserFromContext();
        this.extId = UUID.randomUUID().toString();
        this.createdBy = user.extId();
        this.created = LocalDateTime.now();
        this.updatedBy = user.username();
        this.updated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        var user = getUserFromContext();
        this.extId = UUID.randomUUID().toString();
        this.updatedBy = user.username();
        this.updated = LocalDateTime.now();
    }

}
