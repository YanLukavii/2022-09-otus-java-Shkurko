package ru.otus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Table("addresses")
public class Address {
    @Id
    private String clientId;
    private String street;

    @PersistenceCreator
    public Address(String clientId, String street) {
        this.clientId = clientId;
        this.street = street;
    }

    public Address(String street) {
        this(null, street);
    }

}



