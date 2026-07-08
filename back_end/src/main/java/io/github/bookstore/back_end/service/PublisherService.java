package io.github.bookstore.back_end.service;

import io.github.bookstore.back_end.exception.ApiException;
import io.github.bookstore.back_end.mapper.PublisherMapper;
import io.github.bookstore.back_end.model.Entity.Publisher;
import io.github.bookstore.back_end.model.EntityDTO.PublisherDTO;
import io.github.bookstore.back_end.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;
    private PublisherMapper publisherMapper;

    public ResponseEntity<Publisher> getPublisherByCnpj(String cnpj) {
        Optional<Publisher> isPublisherExist = publisherRepository.findByCnpj(cnpj);

        if (isPublisherExist.isEmpty()) {
            throw new ApiException(String.format("Editora com CNPJ %s não existe.", cnpj), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(isPublisherExist.get());
    }

    public ResponseEntity<Publisher> createPublisher(PublisherDTO publisherDto) {
        Optional<Publisher> isPublisherExist = publisherRepository.findByCnpj(publisherDto.cnpj());

        if (isPublisherExist.isPresent()) {
            throw new ApiException("O CNPJ é inválido.", HttpStatus.BAD_REQUEST);
        }

        Publisher publisher = publisherMapper.toEntity(publisherDto);
        publisherRepository.save(publisher);

        return ResponseEntity.status(HttpStatus.CREATED).body(publisher);

    }

    public ResponseEntity<Map<String, String>> updatePublisher(PublisherDTO publisherDto) {
        Optional<Publisher> isPublisherExist = publisherRepository.findByCnpj(publisherDto.cnpj());

        if (isPublisherExist.isEmpty()) {
            throw new ApiException(String.format("Editora com CNPJ %s não existe.", publisherDto.cnpj()), HttpStatus.BAD_REQUEST);
        }

        Publisher publisher = isPublisherExist.get();
        publisherMapper.updateEntityFromDto(publisherDto, publisher);
        publisher.setUpdatedAt(LocalDateTime.now());
        publisherRepository.save(publisher);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Editora atualizada com sucesso."
        ));

    }

    public ResponseEntity<Map<String, String>> deletePublisher(String cnpj) {
        Optional<Publisher> isPublisherExist = publisherRepository.findByCnpj(cnpj);

        if (isPublisherExist.isEmpty()) {
            throw new ApiException(String.format("Editora com CNPJ %s não existe.", cnpj), HttpStatus.BAD_REQUEST);
        }

        publisherRepository.delete(isPublisherExist.get());

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Editora removida com sucesso."
        ));

    }

}
