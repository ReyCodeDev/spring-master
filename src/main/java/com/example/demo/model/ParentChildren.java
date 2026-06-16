package com.example.demo.model;

import com.example.demo.model.enums.ParentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parent_relations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentChildren {

    @Id
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Person parent;

    @Id
    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Person child;

    @Id
    @Enumerated(EnumType.STRING)
    private ParentType type;
}
