package com.demo.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RoleDto {
    private Integer id;
    private String title;
    private String description;
    private Double salary;
}
