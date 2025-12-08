package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.TimesheetDtoRequest;
import by.awesome.sup.entity.common.Timesheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimesheetMapper {
    TimesheetDtoRequest toDto(Timesheet timesheet);
    Timesheet toEntity(TimesheetDtoRequest timesheetDto);

    @Mapping(target = "id", ignore = true)
    Timesheet toCreateEntity(TimesheetDtoRequest timesheetDto);
}
