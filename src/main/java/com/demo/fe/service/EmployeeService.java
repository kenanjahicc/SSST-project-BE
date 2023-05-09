package com.demo.fe.service;

import com.demo.fe.data.entity.EmployeeEntity;
import com.demo.fe.data.repository.EmployeeRepository;
import com.demo.fe.model.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

        public EmployeeDto createEmployee(EmployeeDto employeeDto) {
            EmployeeEntity employee = new EmployeeEntity();
            employee.setName(employeeDto.getName());
            employee.setSalary(employeeDto.getSalary());
            employee.setTeam(employeeDto.getTeam());
            employee.setRole(employeeDto.getRole());

            return employeeDto;
        }

    public EmployeeDto updateEmployeeById(Integer id, EmployeeDto employee) {
        Optional <EmployeeEntity> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            EmployeeEntity employeeEntity = employeeOptional.get();
            employeeEntity.setSalary(1000.0);
            employeeEntity.setTeam("New Team");
            employeeEntity.setRole("New Role");
        }

        return employee;
    }

        public List<EmployeeEntity> getEmployeeList() {
            List<EmployeeEntity> employees = employeeRepository.findAll();
            List<EmployeeDto> result = new ArrayList<>();
            for (EmployeeEntity employee : employees) {
                result.add(new EmployeeDto(
                        employee.getName(),
                        employee.getSalary(),
                        employee.getTeam(),
                        employee.getRole(),
                        false));
            }
            return result;
        }


        public EmployeeEntity getEmployeeById(Integer id) throws Exception {
            log.info("getEmployeeById called with id " + id);
            Optional<EmployeeEntity> result = employeeRepository.findById(id);
            if (result.isPresent()) {
                return result.get();
            } else {
                log.error(String.format("Could not find employee with id %s", id));
                throw new Exception(String.format("Could not find employee with id %s", id));
            }
        }

        public Integer deleteEmployeeById(Integer id) throws Exception  {
            // check whether id exists or not. If not, throw an exception
            this.getEmployeeById(id);

            // delete the account type
            employeeRepository.deleteById(id);
            return 1;
        }
    }
