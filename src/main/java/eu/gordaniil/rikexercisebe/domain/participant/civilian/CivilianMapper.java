package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import org.mapstruct.Mapper;

@Mapper
public interface CivilianMapper {

    CivilianDto toDto(CivilianDao dao);

    CivilianDao toDao(CivilianDto dto);

}
