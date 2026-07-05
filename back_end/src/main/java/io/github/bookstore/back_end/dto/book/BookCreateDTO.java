package io.github.bookstore.back_end.dto.book;

import io.github.bookstore.back_end.models.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookCreateDTO(
     String isbn,
     String title,
     String description,
     String image,
     String publisherCnpj,
     int stockQuantity,
     double price,
     double review,
     Language language,
     int pageCount
) {}
