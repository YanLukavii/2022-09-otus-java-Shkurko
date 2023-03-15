CREATE TABLE clients (
  id varchar(50) not null primary key,
  name VARCHAR(50)
);

CREATE TABLE phones (
  id bigserial not null primary key,
  number varchar(50) not null,
  client_id varchar(50) REFERENCES clients(id)
);

CREATE TABLE addresses (
  client_id varchar(50) not null references clients(id),
  street VARCHAR(50) not null
);


