package io.github.bookstore.back_end.service;

import io.github.bookstore.back_end.dto.exceptions.ApiException;
import io.github.bookstore.back_end.mapper.EmployeeMapper;
import io.github.bookstore.back_end.model.entity.Employee;
import io.github.bookstore.back_end.model.entityDto.EmployeeCreateResponseDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeRequestDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeResponseDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeUpdateRequestDTO;
import io.github.bookstore.back_end.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public EmployeeCreateResponseDTO createEmployee(EmployeeRequestDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new ApiException("Este email ja existe!", HttpStatus.CONFLICT);
        }
        if (employeeRepository.existsByCpf(employeeDTO.cpf())) {
            throw new ApiException("Este CPF ja existe!", HttpStatus.CONFLICT);
        }
        if (employeeRepository.existsByRegistrationNumber(employeeDTO.registrationNumber())) {
            throw new ApiException("Este numero de registro ja existe!", HttpStatus.CONFLICT);
        }
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toCreateResponseDTO(savedEmployee);
    }

    public EmployeeResponseDTO getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ApiException("Funcionario nao encontrado", HttpStatus.NOT_FOUND));
        return employeeMapper.toResponseDTO(employee);
    }

    private Employee findEmployeeEntityById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApiException("Funcionario nao encontrado", HttpStatus.NOT_FOUND));
    }

    public void deleteEmployee(UUID id) {
        Employee employee = findEmployeeEntityById(id);
        employeeRepository.delete(employee);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeUpdateRequestDTO employeeDTO) {
        Employee employee = findEmployeeEntityById(id);
        boolean changed = false;

        if (employeeDTO.name() != null) {
            validateNotBlank(employeeDTO.name(), "Nome");
            if (!Objects.equals(employeeDTO.name(), employee.getName())) {
                employee.setName(employeeDTO.name());
                changed = true;
            }
        }

        if (employeeDTO.email() != null) {
            validateNotBlank(employeeDTO.email(), "Email");
            if (!Objects.equals(employeeDTO.email(), employee.getEmail())) {
                if (employeeRepository.existsByEmail(employeeDTO.email())) {
                    throw new ApiException("Email de funcionario ja existe", HttpStatus.CONFLICT);
                }
                employee.setEmail(employeeDTO.email());
                changed = true;
            }
        }

        if (employeeDTO.cpf() != null) {
            validateNotBlank(employeeDTO.cpf(), "CPF");
            if (!Objects.equals(employeeDTO.cpf(), employee.getCpf())) {
                if (employeeRepository.existsByCpf(employeeDTO.cpf())) {
                    throw new ApiException("CPF de funcionario ja existe", HttpStatus.CONFLICT);
                }
                employee.setCpf(employeeDTO.cpf());
                changed = true;
            }
        }

        if (employeeDTO.registrationNumber() != null) {
            validateNotBlank(employeeDTO.registrationNumber(), "Numero de registro");
            if (!Objects.equals(employeeDTO.registrationNumber(), employee.getRegistrationNumber())) {
                if (employeeRepository.existsByRegistrationNumber(employeeDTO.registrationNumber())) {
                    throw new ApiException("Numero de registro de funcionario ja existe", HttpStatus.CONFLICT);
                }
                employee.setRegistrationNumber(employeeDTO.registrationNumber());
                changed = true;
            }
        }

        if (employeeDTO.password() != null) {
            validateNotBlank(employeeDTO.password(), "Senha");
            if (!Objects.equals(employeeDTO.password(), employee.getPassword())) {
                employee.setPassword(employeeDTO.password());
                changed = true;
            }
        }

        if (!changed) {
            throw new ApiException("Nenhum dado novo foi informado para atualizar", HttpStatus.BAD_REQUEST);
        }

        employee.setUpdatedAt(LocalDateTime.now());
        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDTO(updatedEmployee);
    }

    private void validateNotBlank(String value, String fieldName) {
        if (value.isBlank()) {
            throw new ApiException(fieldName + "não pode ser vazio", HttpStatus.BAD_REQUEST);
        }
    }
}
