package eu.gordaniil.rikexercisebe.domain.participant;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto extends BaseDto {

    @NotNull(message = "'type' cannot be empty or null")
    private ParticipantTypes type;

    private String firstName;

    private String lastName;

    private String personalIdentityNumber;

    private String paymentType; // TODO: CHECK WHETHER THIS SHOULD NOT BE AN 'enum'

    private String additionalInfo;

    // TODO: make assertTrue additional info length depending on the 'type'.
    //  Civilian is 1500 max and company is 5000

}
