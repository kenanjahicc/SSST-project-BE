package com.demo.fe.data.service;

import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.data.repository.EmployeeRepository;
import com.demo.fe.data.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoleDataService {
    @Autowired
    RoleRepository repository;

    @Autowired
    EmployeeRepository employeeRepository;

    public RoleEntity createOrUpdateRole(RoleEntity role) {
        return repository.save(role);
    }

    public List<RoleEntity> getRoleList() {
        log.info("getRoleList() called");
        return repository.findAll();
    }


    public RoleEntity getRoleById(Integer roleId) throws Exception {
        log.info("getRoleById called with roleId " + roleId);
        Optional<RoleEntity> result = repository.findById(roleId);
        if (result.isPresent()) {
            return result.get();
        } else {
            log.error(String.format("Could not find role with id %s", roleId));
            throw new Exception(String.format("Could not find role with id %s", roleId));
        }
    }

    public Integer deleteRoleById(Integer roleId) throws Exception  {
        // check whether id exists or not. If not, throw an exception
        this.getRoleById(roleId);

        if (employeeRepository.findAllByRole(roleId) != null) {
            throw new Exception(String.format("Could not delete role, an employee with role id: {} already exists!", roleId))
        }

        // delete the role
        repository.deleteById(roleId);
        return 1;
    }
}
