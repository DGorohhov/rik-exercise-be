package eu.gordaniil.rikexercisebe.domain.event.list;

import org.mapstruct.Mapper;

@Mapper
public interface EventPreviewMapper {

    EventPreviewVm toVm(EventPreviewDao dao);

}
