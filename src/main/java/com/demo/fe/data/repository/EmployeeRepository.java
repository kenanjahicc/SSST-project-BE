package com.demo.fe.data.repository;

import com.demo.fe.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findAllByRole(Integer id);

    EmployeeEntity findAllByTeam(Integer id);

}
