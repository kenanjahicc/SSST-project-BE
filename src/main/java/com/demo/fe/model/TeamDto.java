package com.demo.fe.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.demo.fe.data.entity.EmployeeEntity;

@Getter
@Setter
@AllArgsConstructor
public class TeamDto {

    private String title;
    private EmployeeEntity employee;
    private double gained;
    private double lost;
    private double profit;
}
