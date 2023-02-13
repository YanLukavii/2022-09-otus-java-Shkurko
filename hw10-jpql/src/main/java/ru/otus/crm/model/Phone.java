package ru.otus.crm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "phones")
@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "phones_SEQ_gen")
    @SequenceGenerator(name = "phones_SEQ_gen", allocationSize = 1)
    Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
