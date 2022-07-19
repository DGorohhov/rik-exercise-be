package eu.gordaniil.rikexercisebe.domain.participant.company;

import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {

    CompanyDto toDto(CompanyDao dao);

    CompanyDao toDao(CompanyDto dto);

}
