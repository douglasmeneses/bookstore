package io.github.bookstore.back_end.model.entityDto;

import java.time.LocalDateTime;

public record EmployeeResponseDTO(
        String name,
        String email,
        String registrationNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
