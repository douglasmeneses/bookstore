package io.github.bookstore.back_end.services;

import io.github.bookstore.back_end.model.Entity.Publisher;
import io.github.bookstore.back_end.model.EntityDTO.BookDTO;
import io.github.bookstore.back_end.exceptions.ApiException;
import io.github.bookstore.back_end.mapper.BookMapper;
import io.github.bookstore.back_end.model.Entity.Book;
import io.github.bookstore.back_end.model.enums.Language;
import io.github.bookstore.back_end.repositories.BookRepository;
import io.github.bookstore.back_end.service.BookService;
import io.github.bookstore.back_end.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private static final String ISBN = "9781234567890";

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookService bookService;

    @Mock
    private PublisherService publisherService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookMapper, bookRepository, publisherService);
    }

    @Test
    void getBookByIsbnWhenBookExistsReturnsBook() {
        Book book = book();
        when(bookRepository.findById(ISBN)).thenReturn(Optional.of(book));

        ResponseEntity<Book> response = bookService.getBookByIsbn(ISBN);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(book, response.getBody());
        verify(bookRepository).findById(ISBN);
    }

    @Test
    void getBookByIsbnWhenBookDoesNotExistThrowsApiException() {
        when(bookRepository.findById(ISBN)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> bookService.getBookByIsbn(ISBN));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Book with isbn 9781234567890 not exist.", exception.getMessage());
    }

    @Test
    void createBookWhenIsbnIsNewSavesAndReturnsCreatedBook() {
        BookDTO dto = createDTO();
        Book book = book();
        when(bookRepository.findById(ISBN)).thenReturn(Optional.empty());
        when(bookMapper.toEntity(dto)).thenReturn(book);

        ResponseEntity<Book> response = bookService.createBook(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(book, response.getBody());
        verify(bookRepository).save(book);
    }

    @Test
    void createBookWhenIsbnAlreadyExistsThrowsApiException() {
        BookDTO dto = createDTO();
        when(bookRepository.findById(ISBN)).thenReturn(Optional.of(book()));

        ApiException exception = assertThrows(ApiException.class, () -> bookService.createBook(dto));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Book with isbn 9781234567890 already exist.", exception.getMessage());
        verifyNoInteractions(bookMapper);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void updateBookWhenBookExistsMapsDtoSavesAndReturnsSuccessMessage() {
        BookDTO dto = updateDTO();
        Book book = book();
        when(bookRepository.findById(ISBN)).thenReturn(Optional.of(book));

        ResponseEntity<Map<String, String>> response = bookService.updateBook(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("message", "Book updated successfully."), response.getBody());
        verify(bookMapper).updateEntityFromDto(dto, book);
        verify(bookRepository).save(book);
    }

    @Test
    void updateBookWhenBookDoesNotExistThrowsApiException() {
        BookDTO dto = updateDTO();
        when(bookRepository.findById(ISBN)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> bookService.updateBook(dto));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Book with isbn 9781234567890 not exist.", exception.getMessage());
        verifyNoInteractions(bookMapper);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void deleteBookWhenBookExistsDeletesAndReturnsSuccessMessage() {
        Book book = book();
        when(bookRepository.findById(ISBN)).thenReturn(Optional.of(book));

        ResponseEntity<Map<String, String>> response = bookService.deleteBook(ISBN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("message", "Book deleted successfully."), response.getBody());
        verify(bookRepository).delete(book);
    }

    @Test
    void deleteBookWhenBookDoesNotExistThrowsApiException() {
        when(bookRepository.findById(ISBN)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> bookService.deleteBook(ISBN));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Book with isbn 9781234567890 not exist.", exception.getMessage());
        verify(bookRepository, never()).delete(any(Book.class));
    }

    private static BookDTO createDTO() {
        return new BookDTO(
                ISBN,
                "Clean Code",
                "A practical handbook of agile software craftsmanship.",
                "clean-code.jpg",
                "12345678000199",
                10,
                99.90,
                4.8,
                Language.ENGLISH,
                464
        );
    }

    private static BookDTO updateDTO() {
        return new BookDTO(
                ISBN,
                "Clean Code - Updated",
                "Updated description.",
                "clean-code-updated.jpg",
                "12345678000199",
                12,
                89.90,
                4.9,
                Language.ENGLISH,
                464
        );
    }

    private static Book book() {
        Book book = new Book();
        book.setIsbn(ISBN);
        book.setTitle("Clean Code");
        book.setDescription("A practical handbook of agile software craftsmanship.");
        book.setImage("clean-code.jpg");
        book.setPublisher(new Publisher());
        book.setStockQuantity(10);
        book.setPrice(99.90);
        book.setReview(4.8);
        book.setLanguage(Language.ENGLISH);
        book.setPageCount(464);
        return book;
    }
}
