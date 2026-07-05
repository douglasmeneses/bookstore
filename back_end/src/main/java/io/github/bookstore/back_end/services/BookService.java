package io.github.bookstore.back_end.services;

import io.github.bookstore.back_end.dto.book.BookCreateDTO;
import io.github.bookstore.back_end.dto.book.BookUpdateDTO;
import io.github.bookstore.back_end.dto.exceptions.ApiException;
import io.github.bookstore.back_end.mapper.BookMapper;
import io.github.bookstore.back_end.models.Book;
import io.github.bookstore.back_end.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private BookMapper bookMapper;
    private BookRepository bookRepository;

    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        Optional<Book> isBookExist = bookRepository.findById(isbn);

        if (isBookExist.isEmpty()) {
            throw new ApiException(String.format("Book with isbn %s not exist.", isbn), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(isBookExist.get());
    }

    public ResponseEntity<Book> createBook(BookCreateDTO bookCreateDTO) {
        Optional<Book> isBookExist = bookRepository.findById(bookCreateDTO.isbn());

        if (isBookExist.isPresent()) {
            throw new ApiException(String.format("Book with isbn %s already exist.", bookCreateDTO.isbn()), HttpStatus.BAD_REQUEST);
        }

        Book book = bookMapper.toEntity(bookCreateDTO);
        bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

    public ResponseEntity<Map<String, String>> updateBook(BookUpdateDTO bookUpdateDTO) {
        Optional<Book> isBookExist = bookRepository.findById(bookUpdateDTO.isbn());

        if (isBookExist.isEmpty()) {
            throw new ApiException(String.format("Book with isbn %s not exist.", bookUpdateDTO.isbn()), HttpStatus.BAD_REQUEST);
        }

        Book book = isBookExist.get();
        bookMapper.updateEntityFromDto(bookUpdateDTO, book);
        bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Book updated successfully."
        ));

    }

    public ResponseEntity<Map<String, String>> deleteBook(String isbn) {
        Optional<Book> isBookExist = bookRepository.findById(isbn);

        if (isBookExist.isEmpty()) {
            throw new ApiException(String.format("Book with isbn %s not exist.", isbn), HttpStatus.BAD_REQUEST);
        }

        bookRepository.delete(isBookExist.get());

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Book deleted successfully."
        ));

    }

}
