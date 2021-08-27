CREATE TABLE employees (
    id              INTEGER NOT NULL PRIMARY KEY,
    first_name      VARCHAR(30) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    age             INTEGER NOT NULL,
    gender          ENUM('M','F') NOT NULL
);

CREATE TABLE employee_addresses (
    id              INTEGER NOT NULL PRIMARY KEY,
    employee_id     INTEGER NOT NULL REFERENCES employees(id),
    address         VARCHAR(128) NOT NULL
);
