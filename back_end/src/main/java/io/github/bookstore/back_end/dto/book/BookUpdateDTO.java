package io.github.bookstore.back_end.dto.book;

import io.github.bookstore.back_end.models.enums.Language;

public record BookUpdateDTO(
        String isbn,
        String title,
        String description,
        String image,
        String publisherCnpj,
        Integer stockQuantity,
        Double price,
        Double review,
        Language language,
        Integer pageCount
) {}
