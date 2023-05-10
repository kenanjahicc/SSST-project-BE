package com.demo.fe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class RoleDto {
    private String title;
    private String description;
    private Double income;
}
