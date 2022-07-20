package eu.gordaniil.rikexercisebe.domain.event.list;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventPreviewVm {

    @JsonIgnore
    private String id;

    private String extId;

    private String name;

    private LocalDateTime date;

}
