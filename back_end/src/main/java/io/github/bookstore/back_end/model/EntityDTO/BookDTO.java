package io.github.bookstore.back_end.model.EntityDTO;

import io.github.bookstore.back_end.model.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookDTO(
       @Size(min = 13,max = 13, message = "Deve ter 13 caracteres.")
       @NotBlank(message = "Isbn não pode ser vazio.")
       String isbn,
       @Size(min = 3,max = 150, message = "Deve ter entre 3 a 150 caracteres.")
       @NotBlank(message = "Título não pode ser vazio.")
       String title,
       @NotBlank(message = "Descrição não pode ser vazio.")
       String description,
       @NotBlank(message = "Imagem não pode ser vazio.")
       String image,
       @NotBlank(message = "Editora pode ser vazio.")
       String publisherId,
       @NotNull(message = "Quantidade do estoque não pode ser nulo.")
       int stockQuantity,
       @NotNull(message = "Preço não pode ser nulo.")
       double price,
       double review,
       @NotNull(message = "Idioma não pode ser nulo.")
       Language language,
       @NotNull(message = "Número de páginas não pode ser nulo.")
       int pageCount
) {}
