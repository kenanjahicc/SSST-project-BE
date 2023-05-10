package com.demo.fe.service;

import com.demo.fe.data.entity.EmployeeEntity;
import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.data.entity.TeamEntity;
import com.demo.fe.data.service.EmployeeDataService;
import com.demo.fe.data.service.RoleDataService;
import com.demo.fe.data.service.TeamDataService;
import com.demo.fe.model.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeDataService employeeDataService;

    @Autowired
    RoleDataService roleDataService;

    @Autowired
    TeamDataService teamDataService;

    public List<EmployeeEntity> getEmployeeList() {
        log.info("getEmployeeList() called");
        return employeeDataService.getEmployeeList();
    }

    public EmployeeEntity getEmployeeById(Integer id) throws Exception {
        log.info("getEmployeeById() called with id: {}", id);
        return employeeDataService.getEmployeeById(id);
    }

    public EmployeeEntity validatePayloadAndReturnEntity(Integer employeeId, EmployeeDto employee) throws Exception {
        Objects.requireNonNull(employee.getName(), "Employee name is required");
        if (employee.getName().isEmpty()){
            log.info("Employee name is required");
            throw new Exception("Employee name is required");
        }
        Objects.requireNonNull(employee.getSalary(), "Employee salary is required");
        if (employee.getSalary() == 0){
            log.info("Employee salary is required");
            throw new Exception("Employee salary is required");
        }

        // validation
        // 1. update
        if (employeeId != null) {
            EmployeeEntity existingEntity = employeeDataService.getEmployeeById(employeeId);
            if (existingEntity == null) {
                log.info("Employee with id {} does not exist.", employeeId);
                throw new Exception(String.format("Employee with id '%s' does not exist.", employeeId));
            }

        }

        TeamEntity team = teamDataService.getTeamById(employee.getTeam());
        RoleEntity role = roleDataService.getRoleById(employee.getRole());

        EmployeeEntity employeeDb = new EmployeeEntity();
        // in case of insert employeeId will be created on repository level
        if (employeeId != null) {
            employeeDb.setId(employeeId);
        }
        employeeDb.setName(employee.getName());
        employeeDb.setSalary(employee.getSalary());
        employeeDb.setTeam(team);
        employeeDb.setRole(role);

        return employeeDb;
    }

    public EmployeeEntity createEmployee(EmployeeDto employee) throws Exception {
        log.info("createEmployee() called with data {}: ", employee);

        EmployeeEntity employeeDb = this.validatePayloadAndReturnEntity(null, employee);

        EmployeeEntity createdEmployee = employeeDataService.createOrUpdateEmployee(employeeDb);

        return employeeDataService.getEmployeeById(createdEmployee.getId());
    }

    public EmployeeEntity updateEmployeeById(Integer employeeId, EmployeeDto employee) throws Exception {
        log.info("updateEmployeeById() called with id: {}", employeeId);

        EmployeeEntity employeeDb = this.validatePayloadAndReturnEntity(employeeId, employee);

        EmployeeEntity createdEmployee = employeeDataService.createOrUpdateEmployee(employeeDb);

        // go to db and get all objects
        return employeeDataService.getEmployeeById(createdEmployee.getId());
    }

    public EmployeeEntity removeEmployeeFromOrganization(Integer employeeId) throws Exception {
        log.info("updateEmployeeById() called with id: {}", employeeId);

        EmployeeEntity existingEntity = employeeDataService.getEmployeeById(employeeId);
        existingEntity.setId(existingEntity.getId());
        existingEntity.setName(existingEntity.getName());
        existingEntity.setSalary(existingEntity.getSalary());
        existingEntity.setTeam(null);
        existingEntity.setRole(null);

        EmployeeEntity createdEmployee = employeeDataService.createOrUpdateEmployee(existingEntity);

        // go to db and get all objects
        return employeeDataService.getEmployeeById(createdEmployee.getId());
    }

    public Integer deleteEmployeeById(Integer id) throws Exception {
        log.info("deleteEmployeeById() called with id: {}", id);
        return employeeDataService.deleteEmployeeById(id);
    }
}
