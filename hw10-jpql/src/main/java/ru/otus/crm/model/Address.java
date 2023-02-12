package ru.otus.crm.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "street")
    private String street;
}



