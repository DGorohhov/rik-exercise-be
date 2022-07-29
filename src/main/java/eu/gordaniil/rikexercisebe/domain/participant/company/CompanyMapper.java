package eu.gordaniil.rikexercisebe.domain.participant.company;

import org.mapstruct.*;

@Mapper
public interface CompanyMapper {

    CompanyDto toDto(CompanyDao dao);

    CompanyDao toDao(CompanyDto dto);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "dto.legalName", target = "dao.legalName"),
            @Mapping(source = "dto.registerCode", target = "dao.registerCode"),
            @Mapping(source = "dto.numberOfParticipants", target = "dao.numberOfParticipants"),
            @Mapping(source = "dto.paymentType", target = "dao.paymentType"),
            @Mapping(source = "dto.additionalInfo", target = "dao.additionalInfo"),
    })
    void toExistingCompany(
            @MappingTarget CompanyDao dao,
            CompanyDto dto
    );

}
