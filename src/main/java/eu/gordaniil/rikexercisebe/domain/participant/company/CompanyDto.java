package eu.gordaniil.rikexercisebe.domain.participant.company;

import eu.gordaniil.rikexercisebe.domain.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto extends BaseDto {

    private String legalName;

    private String registerCode;

    private Integer numberOfParticipants;

    private String paymentType; // TODO: CHECK WHETHER THIS SHOULD NOT BE AN 'enum'

    @Length(max = 5000, message = "'additionalInfo' should not contain more that 5000 characters")
    private String additionalInfo;

}
