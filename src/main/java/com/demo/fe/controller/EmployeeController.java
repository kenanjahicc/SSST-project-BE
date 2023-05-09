package com.demo.fe.controller;

import com.demo.fe.data.entity.EmployeeEntity;
import com.demo.fe.model.EmployeeDto;
import com.demo.fe.model.ErrorObject;
import com.demo.fe.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/employee")
@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    ResponseEntity<List<EmployeeEntity>> getEmployeeList() {
        log.info("getEmployeeList() called");
        return new ResponseEntity<>(employeeService.getEmployeeList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getEmployeeById(@PathVariable Integer id) {
        log.info("getEmployeeById() called");
        try {
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createEmployee(@RequestBody EmployeeDto employee) {
        log.info("createEmployee() called");
        try {
            return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateEmployeeById(@PathVariable Integer id,
                                                @RequestBody EmployeeDto employee) {
        log.info("updateEmployeeById() called");
        try {
            return new ResponseEntity<>(employeeService.updateEmployeeById(id, employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteEmployeeById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
