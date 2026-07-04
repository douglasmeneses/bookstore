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
    @Transactional
    public EmployeeCreateResponseDTO createEmployee(EmployeeRequestDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new IllegalArgumentException("Este email já existe!");
        }
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toCreateResponseDTO(savedEmployee);
    }

    public EmployeeResponseDTO getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        return EmployeeMapper.toResponseDTO(employee);
    }
    private Employee findEmployeeEntityById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
    }

    public void deleteEmployee(UUID id) {
        Employee employee = findEmployeeEntityById(id);
        employeeRepository.delete(employee);
    }
    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO employeeDTO){
        Employee employee = findEmployeeEntityById(id);

        boolean emailChanged = !employeeDTO.email().equals(employee.getEmail());

        if(emailChanged && employeeRepository.existsByEmail(employeeDTO.email())){
            throw new IllegalArgumentException("Email de funcionário já existe");
        }

        EmployeeMapper.updateEntity(employee, employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponseDTO(updatedEmployee);
    }
}
