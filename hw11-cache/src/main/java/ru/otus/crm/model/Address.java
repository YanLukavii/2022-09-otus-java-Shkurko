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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "address_SEQ_gen")
    @SequenceGenerator(name = "address_SEQ_gen", allocationSize = 1)
    private Long id;

    @Column(name = "street")
    private String street;
}



