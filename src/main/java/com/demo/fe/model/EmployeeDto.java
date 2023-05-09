package com.demo.fe.model;

import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.data.entity.TeamEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EmployeeDto {
    private String name;

    private Double salary;

    private TeamEntity team;

    private RoleEntity role;

}
