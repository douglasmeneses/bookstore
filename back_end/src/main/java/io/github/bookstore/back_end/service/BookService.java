package io.github.bookstore.back_end.service;

import io.github.bookstore.back_end.model.Entity.Publisher;
import io.github.bookstore.back_end.model.EntityDTO.BookCreateDTO;
import io.github.bookstore.back_end.model.EntityDTO.BookUpdateDTO;
import io.github.bookstore.back_end.exceptions.ApiException;
import io.github.bookstore.back_end.mapper.BookMapper;
import io.github.bookstore.back_end.model.Entity.Book;
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
    private PublisherService publisherService;

    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        Optional<Book> isBookExist = bookRepository.findById(isbn);

        if (isBookExist.isEmpty()) {
            throw new ApiException(String.format("Livro com isbn %s não existe.", isbn), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(isBookExist.get());
    }

    public ResponseEntity<Book> createBook(BookCreateDTO bookCreateDTO) {
        Optional<Book> isBookExist = bookRepository.findById(bookCreateDTO.isbn());

        if (isBookExist.isPresent()) {
            throw new ApiException(String.format("Livro com isbn %s já existe.", bookCreateDTO.isbn()), HttpStatus.BAD_REQUEST);
        }

        Optional<Publisher> isPublishExist = publisherService.findPublisherById(bookCreateDTO.publisherId());

        if (isPublishExist.isEmpty()) {
            throw new ApiException("Editora com não foi encontrada.", HttpStatus.BAD_REQUEST);
        }

        Book book = bookMapper.toEntity(bookCreateDTO);
        book.setPublisher(isPublishExist.get());
        bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

    public ResponseEntity<Map<String, String>> updateBook(BookUpdateDTO bookUpdateDTO) {
        Optional<Book> isBookExist = bookRepository.findById(bookUpdateDTO.isbn());

        if (isBookExist.isEmpty()) {
            throw new ApiException(String.format("Livro com isbn %s não existe.", bookUpdateDTO.isbn()), HttpStatus.BAD_REQUEST);
        }

        Book book = isBookExist.get();
        bookMapper.updateEntityFromDto(bookUpdateDTO, book);
        bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Livro atualizado com sucesso."
        ));

    }

    public ResponseEntity<Map<String, String>> deleteBook(String isbn) {
        Optional<Book> isBookExist = bookRepository.findById(isbn);

        if (isBookExist.isEmpty()) {
            throw new ApiException(String.format("Livro com isbn %s não existe.", isbn), HttpStatus.BAD_REQUEST);
        }

        bookRepository.delete(isBookExist.get());

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Livro removido com sucesso."
        ));

    }

}
