package io.github.bookstore.back_end.repositories;

import io.github.bookstore.back_end.model.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    Optional<Publisher> findByCnpj(String cnpj);
}
