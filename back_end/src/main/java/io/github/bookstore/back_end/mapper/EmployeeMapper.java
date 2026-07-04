package io.github.bookstore.back_end.mapper;

import io.github.bookstore.back_end.model.entity.Employee;
import io.github.bookstore.back_end.model.entityDto.EmployeeCreateResponseDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeRequestDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeResponseDTO;

import java.time.LocalDateTime;

public class EmployeeMapper {
    private  EmployeeMapper(){
    }
    public static Employee toEntity(EmployeeRequestDTO dto) {
        LocalDateTime now = LocalDateTime.now();

        return new Employee(
                null,
                dto.registrationNumber(),
                dto.cpf(),
                dto.name(),
                dto.email(),
                dto.password(),
                now,
                now
        );
    }
    public static EmployeeResponseDTO toResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getName(),
                employee.getEmail(),
                employee.getRegistrationNumber(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    public static EmployeeCreateResponseDTO toCreateResponseDTO(Employee employee) {
        return new EmployeeCreateResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail()

        );
    }

    public static void updateEntity(Employee employee, EmployeeRequestDTO dto) {
        employee.setName(dto.name());
        employee.setCpf(dto.cpf());
        employee.setRegistrationNumber(dto.registrationNumber());
        employee.setEmail(dto.email());
        employee.setPassword(dto.password());
        employee.setUpdatedAt(LocalDateTime.now());
    }
}
