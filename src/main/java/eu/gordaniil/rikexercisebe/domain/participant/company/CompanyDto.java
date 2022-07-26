package eu.gordaniil.rikexercisebe.domain.participant.company;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import eu.gordaniil.rikexercisebe.validation.ValidationConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto extends BaseDto {

    @NotEmpty(message = "'firstName' cannot be null or empty")
    private String legalName;

    @NotEmpty(message = "'registerCode' cannot be null or empty")
    @Pattern(regexp = ValidationConstraint.COMPANY_REGISTER_CODE_REGEXP, message = "'registerCode' must be a 8 characters long numeric string")
    private String registerCode;

    @NotNull(message = "'numberOfParticipants' cannot be null or empty")
    private Integer numberOfParticipants;

    @NotEmpty(message = "'paymentType' cannot be null or empty")
    private String paymentType; // TODO: CHECK WHETHER THIS SHOULD NOT BE AN 'enum'

    @Length(max = 5000, message = "'additionalInfo' should not contain more that 5000 characters")
    private String additionalInfo;

}
