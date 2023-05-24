package com.hami.Entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;


    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }
    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
