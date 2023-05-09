package com.demo.fe.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RoleEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
