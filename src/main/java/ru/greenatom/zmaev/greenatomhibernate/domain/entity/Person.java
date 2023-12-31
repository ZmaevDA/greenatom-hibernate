package ru.greenatom.zmaev.greenatomhibernate.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age")
    @NonNull
    private Integer age;

    @Column(name = "firstname")
    @NonNull
    private String firstname;

    @Column(name = "lastname")
    @NonNull
    private String lastname;

    @Column(name = "is_admin")
    @NonNull
    private Boolean isAdmin;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

}
