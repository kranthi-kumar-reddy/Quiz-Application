package com.digit.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Person {
    @Id
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private List<Address> address;
}
