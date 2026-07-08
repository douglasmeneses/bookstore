package io.github.bookstore.back_end.controllers;

import io.github.bookstore.back_end.model.EntityDTO.BookCreateDTO;
import io.github.bookstore.back_end.model.EntityDTO.BookUpdateDTO;
import io.github.bookstore.back_end.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookIsbn(@Valid @PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.createBook(bookCreateDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookUpdateDTO bookUpdateDTO) {
        return bookService.updateBook(bookUpdateDTO);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBookByIsbn(@Valid @PathVariable String isbn) {
        return bookService.deleteBook(isbn);
    }

}
