package eu.gordaniil.rikexercisebe.domain.event;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto extends BaseDto {

    private String name;

    private LocalDateTime date;

    private String location;

    private String additionalInfo; // TODO: max is 1000

}
