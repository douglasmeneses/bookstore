package io.github.bookstore.back_end.controllers;

import io.github.bookstore.back_end.model.EntityDTO.PublisherDTO;
import io.github.bookstore.back_end.service.PublisherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/publisher")
@RestController
@AllArgsConstructor
public class PublisherController {
    
    private PublisherService publisherService;

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> getPublisherCnpj(@Valid @PathVariable @Size(max = 14, min = 14, message = "CNPJ deve conter 14 caracteres.") String cnpj) {
        return publisherService.getPublisherByCnpj(cnpj);
    }

    @PostMapping
    public ResponseEntity<?> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.createPublisher(publisherDTO);
    }

    @PutMapping
    public ResponseEntity<?> updatePublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.updatePublisher(publisherDTO);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<?> deletePublisherByIsbn(@Valid @PathVariable @Size(max = 14, min = 14, message = "CNPJ deve conter 14 caracteres.") String cnpj) {
        return publisherService.deletePublisher(cnpj);
    }
    
}
