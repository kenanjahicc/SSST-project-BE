package com.demo.fe.controller;

import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.model.ErrorObject;
import com.demo.fe.model.RoleDto;
import com.demo.fe.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/role")
@RestController
@Slf4j
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("")
    ResponseEntity<List<RoleEntity>> getRoleList() {
        log.info("getRoleList() called");
        return new ResponseEntity<>(roleService.getRoleList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getRoleById(@PathVariable Integer id) {
        log.info("getRoleById() called");
        try {
            return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createRole(@RequestBody RoleDto role) {
        log.info("createRole() called");
        try {
            return new ResponseEntity<>(roleService.createRole(role), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateRoleById(@PathVariable Integer id,
                                              @RequestBody RoleDto role) {
        log.info("updateRoleById() called");
        try {
            return new ResponseEntity<>(roleService.updateRoleById(id, role), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteRoleById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(roleService.deleteRoleById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
