package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String dni;

    @OneToMany(mappedBy = "parent")
    private List<ParentChildren> childrenRelations;

    @OneToMany(mappedBy = "child")
    private List<ParentChildren> parentRelations;
}
