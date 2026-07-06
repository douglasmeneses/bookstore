package io.github.bookstore.back_end.model.entityDto;

import jakarta.validation.constraints.Email;

public record EmployeeUpdateRequestDTO(
        String name,
        String cpf,
        String registrationNumber,
        String password,
        @Email
        String email
) {
}