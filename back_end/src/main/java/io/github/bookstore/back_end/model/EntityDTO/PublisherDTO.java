package io.github.bookstore.back_end.model.EntityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublisherDTO(
        @Size(max = 14, min = 14, message = "CNPJ deve conter 14 caracteres.")
        @NotBlank(message = "CNPJ não pode estar vazio.")
        String cnpj,
        @Size(min = 2, max = 120, message = "Nome deve conter entre 2 a 120 caracteres")
        @NotBlank(message = "Nome não pode estar vazio.")
        String name,
        String logo,
        String about
) {
}
