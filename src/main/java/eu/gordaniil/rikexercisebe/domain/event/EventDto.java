package eu.gordaniil.rikexercisebe.domain.event;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto extends BaseDto {

    @NotEmpty(message = "'name' cannot be null or empty")
    private String name;

    @NotNull(message = "'date' cannot be null")
    @Future(message = "'date' must be in the future")
    private LocalDateTime date;

    @NotEmpty(message = "'location' cannot be null or empty")
    private String location;

    @Length(max = 1000, message = "'additionalInfo' should not contain more that 1500 characters")
    private String additionalInfo;

}
