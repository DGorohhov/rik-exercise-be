package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CivilianDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String personalIdentityNumber;

    private String paymentType; // TODO: CHECK WHETHER THIS SHOULD NOT BE AN 'enum'

    @Length(max = 1500, message = "'additionalInfo' should not contain more that 1500 characters")
    private String additionalInfo;

    // TODO: make assertTrue additional info length depending on the 'type'.
    //  Civilian is 1500 max and company is 5000

}
