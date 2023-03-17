package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Table("clients")
public class Client implements Persistable<String> {

    @Id
    private String id;
    private String name;

    @MappedCollection(idColumn = "client_id")
    private Address address;

    @MappedCollection(idColumn = "client_id")
    private Set<Phone> phones;

    @Transient
    private boolean isNew;

    public Client() {
        this.id = "c:" + System.currentTimeMillis();
        this.isNew = true;
    }

    @PersistenceCreator
    public Client(String id, String name, Address address, Set<Phone> phones) {
        this(id, name, address, phones, false);
    }
}
