package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import eu.gordaniil.rikexercisebe.validation.ValidationConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CivilianDto extends BaseDto {

    @NotEmpty(message = "'firstName' cannot be null or empty")
    private String firstName;

    @NotEmpty(message = "'lastName' cannot be null or empty")
    private String lastName;

    @NotEmpty(message = "'lastName' cannot be null or empty")
    @Pattern(regexp = ValidationConstraint.PERSONAL_IDENTIFICATION_CODE_REGEXP, message = "'personalIdentityNumber' must be a 11 characters long numeric string")
    private String personalIdentityNumber;

    @NotEmpty(message = "'paymentType' cannot be null or empty")
    private String paymentType; // TODO: CHECK WHETHER THIS SHOULD NOT BE AN 'enum'

    @Length(max = 1500, message = "'additionalInfo' should not contain more that 1500 characters")
    private String additionalInfo;

}
