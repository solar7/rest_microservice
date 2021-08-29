package com.payeye.app.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @Min(1)
    @Max(100)
    @Column
    private int age;

    @NotNull
    @Column
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Address> addresses;

}
