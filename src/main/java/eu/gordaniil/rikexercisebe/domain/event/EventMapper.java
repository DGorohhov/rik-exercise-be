package eu.gordaniil.rikexercisebe.domain.event;

import org.mapstruct.*;

@Mapper
public interface EventMapper {

    EventDto toDto(EventDao dao);

    EventDao toDao(EventDto dto);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "dto.name", target = "dao.name"),
            @Mapping(source = "dto.date", target = "dao.date"),
            @Mapping(source = "dto.location", target = "dao.location"),
            @Mapping(source = "dto.additionalInfo", target = "dao.additionalInfo"),
    })
    void toExistingDao(
            @MappingTarget EventDao dao,
            EventDto dto
    );

}
