package com.demo.fe.data.repository;

import com.demo.fe.data.entity.EmployeeEntity;
import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.data.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findAllByRole(RoleEntity role);

    EmployeeEntity findAllByTeam(TeamEntity team);

}
