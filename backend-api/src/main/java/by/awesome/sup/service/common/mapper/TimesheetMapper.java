package by.awesome.sup.service.common.mapper;

import by.awesome.sup.dto.common.TimesheetDto;
import by.awesome.sup.entity.common.Timesheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimesheetMapper {
    TimesheetDto toDto(Timesheet timesheet);
    Timesheet toEntity(TimesheetDto timesheetDto);

    @Mapping(target = "id", ignore = true)
    Timesheet toCreateEntity(TimesheetDto timesheetDto);
}
