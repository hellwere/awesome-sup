package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.dto.common.TimesheetUpdateDtoRequest;
import by.awesome.sup.entity.common.Timesheet;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TimesheetMapper {
    TimesheetDtoResponse toDto(Timesheet timesheet);
    Timesheet toEntity(TimesheetDtoRequest timesheetDto);
    @Named("merge")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(TimesheetUpdateDtoRequest dto, @MappingTarget Timesheet timesheet);

    @Mapping(target = "id", ignore = true)
    Timesheet toCreateEntity(TimesheetDtoRequest timesheetDto);
}
