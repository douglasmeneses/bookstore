package io.github.bookstore.back_end.model.entityDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        @NotBlank
        String registrationNumber,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email

) {
}
