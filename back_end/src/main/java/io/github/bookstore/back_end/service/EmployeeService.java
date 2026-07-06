package io.github.bookstore.back_end.service;

import io.github.bookstore.back_end.mapper.EmployeeMapper;
import io.github.bookstore.back_end.model.entity.Employee;
import io.github.bookstore.back_end.model.entityDto.EmployeeCreateResponseDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeResponseDTO;
import io.github.bookstore.back_end.model.entityDto.EmployeeRequestDTO;
import io.github.bookstore.back_end.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public EmployeeCreateResponseDTO createEmployee(EmployeeRequestDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new IllegalArgumentException("Este email já existe!");
        }
        if (employeeRepository.existsByCpf(employeeDTO.cpf())) {
            throw new IllegalArgumentException("Este CPF ja existe!");
        }
        if (employeeRepository.existsByRegistrationNumber(employeeDTO.registrationNumber())) {
            throw new IllegalArgumentException("Este numero de registro ja existe!");
        }
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toCreateResponseDTO(savedEmployee);
    }

    public EmployeeResponseDTO getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        return employeeMapper.toResponseDTO(employee);
    }

    private Employee findEmployeeEntityById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
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
    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO employeeDTO) {
        Employee employee = findEmployeeEntityById(id);

        boolean emailChanged = !employeeDTO.email().equals(employee.getEmail());
        boolean cpfChanged = !employeeDTO.cpf().equals(employee.getCpf());
        boolean registrationNumberChanged = !employeeDTO.registrationNumber().equals(employee.getRegistrationNumber());

        if (emailChanged && employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new IllegalArgumentException("Email de Funcionário já existe");
        }
        if (cpfChanged && employeeRepository.existsByCpf(employeeDTO.cpf())) {
            throw new IllegalArgumentException("CPF de Funcionario ja existe");
        }
        if (registrationNumberChanged && employeeRepository.existsByRegistrationNumber(employeeDTO.registrationNumber())) {
            throw new IllegalArgumentException("Numero de registro de Funcionario ja existe");
        }

        employeeMapper.updateEntity(employee, employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDTO(updatedEmployee);
    }
}
