package io.github.bookstore.back_end.model.entityDto;

import java.util.UUID;

public record EmployeeCreateResponseDTO(
        UUID id,
        String name,
        String email
) {
}
