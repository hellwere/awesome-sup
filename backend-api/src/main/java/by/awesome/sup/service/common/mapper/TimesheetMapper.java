package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.dto.common.TimesheetDtoResponse;
import by.awesome.sup.entity.common.Timesheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TimesheetMapper {
    TimesheetDtoResponse toDto(Timesheet timesheet);
    Timesheet toEntity(TimesheetDtoRequest timesheetDto);
    @Named("merge")
    @Mapping(target = "id", ignore = true)
    void merge(TimesheetDtoRequest dto, @MappingTarget Timesheet timesheet);

    @Mapping(target = "id", ignore = true)
    Timesheet toCreateEntity(TimesheetDtoRequest timesheetDto);
}
