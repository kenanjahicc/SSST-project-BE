package com.demo.fe.model;
import lombok.*;
import com.demo.fe.data.entity.EmployeeEntity;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class TeamDto {

    private String title;
    private List<Integer> employees;
    private double gained;
    private double lost;
    private double profit;
}
