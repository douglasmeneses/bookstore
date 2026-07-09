package io.github.bookstore.back_end.model.Entity;

import io.github.bookstore.back_end.model.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Book {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String isbn;
    private String title;
    private String description;
    private String image;
    @JoinColumn(name = "publisher_id")
    @OneToOne
    private Publisher publisher;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    private double price;
    private double review;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Column(name = "page_count")
    private int pageCount;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
