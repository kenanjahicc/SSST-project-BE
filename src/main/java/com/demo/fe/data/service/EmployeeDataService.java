package com.demo.fe.data.service;

import com.demo.fe.data.entity.EmployeeEntity;
import com.demo.fe.data.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeDataService {
    @Autowired
    EmployeeRepository repository;

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity employee) {
        return repository.save(employee);
    }

    public List<EmployeeEntity> getEmployeeList() {
        log.info("getEmployeeList() called");
        return repository.findAll();
    }


    public EmployeeEntity getEmployeeById(Integer employeeId) throws Exception {
        log.info("getEmployeeById called with employeeId " + employeeId);
        Optional<EmployeeEntity> result = repository.findById(employeeId);
        if (result.isPresent()) {
            return result.get();
        } else {
            log.error(String.format("Could not find employee with id %s", employeeId));
            throw new Exception(String.format("Could not find employee with id %s", employeeId));
        }
    }

    public Integer deleteEmployeeById(Integer employeeId) throws Exception  {
        // check whether id exists or not. If not, throw an exception
        this.getEmployeeById(employeeId);

        // delete the employee
        repository.deleteById(employeeId);
        return 1;
    }
}
