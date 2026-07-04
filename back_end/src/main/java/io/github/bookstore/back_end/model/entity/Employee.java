package io.github.bookstore.back_end.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID id;
    private String registrationNumber;
    private String cpf;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Employee(UUID id, String registrationNumber, String cpf, String name, String email, String password, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createAt;
        this.updatedAt = updateAt;
    }

}
