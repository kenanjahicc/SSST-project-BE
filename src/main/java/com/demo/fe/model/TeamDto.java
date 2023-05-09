package com.demo.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor

public class TeamDto {
    private Integer id;
    private String name;
    private Double monthlyIncome;
}
