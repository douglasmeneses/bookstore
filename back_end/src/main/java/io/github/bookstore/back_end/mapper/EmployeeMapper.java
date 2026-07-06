package io.github.bookstore.back_end.mapper;

import io.github.bookstore.back_end.model.entity.Employee;
import io.github.bookstore.back_end.model.entityDto.EmployeeCreateResponseDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeRequestDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Employee toEntity(EmployeeRequestDTO dto);

    EmployeeResponseDTO toResponseDTO(Employee employee);

    EmployeeCreateResponseDTO toCreateResponseDTO(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateEntity(@MappingTarget Employee employee, EmployeeRequestDTO dto);
}
