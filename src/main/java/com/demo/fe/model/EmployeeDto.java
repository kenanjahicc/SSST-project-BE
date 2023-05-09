package com.demo.fe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EmployeeDto {
    private String name;

    private Double salary;

    private Team team;

    private Role role;
}
