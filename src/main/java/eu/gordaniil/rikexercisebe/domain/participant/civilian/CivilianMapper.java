package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import org.mapstruct.*;

@Mapper
public interface CivilianMapper {

    CivilianDto toDto(CivilianDao dao);

    CivilianDao toDao(CivilianDto dto);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "dto.firstName", target = "dao.firstName"),
            @Mapping(source = "dto.lastName", target = "dao.lastName"),
            @Mapping(source = "dto.personalIdentityNumber", target = "dao.personalIdentityNumber"),
            @Mapping(source = "dto.paymentType", target = "dao.paymentType"),
            @Mapping(source = "dto.additionalInfo", target = "dao.additionalInfo"),
    })
    void toExistingCivilian(
        @MappingTarget CivilianDao dao,
        CivilianDto dto
    );

}
