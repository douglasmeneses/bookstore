package io.github.bookstore.back_end.model.EntityDTO;

import io.github.bookstore.back_end.model.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookCreateDTO(
     @Size(min = 13,max = 13, message = "Deve ter 13 caracteres.")
     @NotBlank(message = "Não pode ser vazio.")
     String isbn,
     @NotBlank(message = "Não pode ser vazio.")
     String title,
     @NotBlank(message = "Não pode ser vazio.")
     String description,
     @NotBlank(message = "Não pode ser vazio.")
     String image,
     @NotBlank(message = "Não pode ser vazio.")
     String publisherId,
     @NotNull(message = "Não pode ser nulo.")
     int stockQuantity,
     @NotNull(message = "Não pode ser nulo.")
     double price,
     double review,
     @NotNull(message = "Não pode ser nulo.")
     Language language,
     @NotNull(message = "Não pode ser nulo.")
     int pageCount
) {}
