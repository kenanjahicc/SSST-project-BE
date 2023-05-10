package com.demo.fe.service;

import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.data.service.RoleDataService;
import com.demo.fe.model.RoleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RoleService {
    @Autowired
    RoleDataService service;

    public List<RoleEntity> getRoleList() {
        log.info("getRoleList() called");
        return service.getRoleList();
    }

    public RoleEntity getRoleById(Integer id) throws Exception  {
        log.info("getRoleById() called with id: {}", id);
        return service.getRoleById(id);
    }

    public RoleEntity validatePayloadAndReturnEntity(Integer roleId, RoleDto role) throws Exception {
        Objects.requireNonNull(role.getTitle(), "Role Title is required");
        if (role.getTitle().isEmpty()){
            log.info("Role Title is required!");
            throw new Exception("Role Title is required!");
        }

        Objects.requireNonNull(role.getIncome(), "Income is required");

        // validation
        // 1. update
        if (roleId != null) {
            RoleEntity roleEntity = service.getRoleById(roleId);

            if(!Objects.equals(roleEntity.getTitle(), role.getTitle())) {
                if (service.getRoleByTitle(role.getTitle()) != null){
                    log.info("Role with name {} already exists. It is not possible to update it.", role.getTitle());
                    throw new Exception(String.format("Role with name '%s' already exists. It is not possible to update it.", role.getTitle()));
                }
            }
        } else { // 2. insert
            // in a case of insert (roleId is null) check if name already exists
            if (service.getRoleByTitle(role.getTitle()) != null){
                log.info("Role with name {} already exists.", role.getTitle());
                throw new Exception(String.format("Role with name '%s' already exists.", role.getTitle()));
            }
        }

        RoleEntity roleDb = new RoleEntity();
        // in case of insert roleId will be created on repository level
        if (roleId != null) {
            roleDb.setId(roleId);
        }

        roleDb.setTitle(role.getTitle());
        roleDb.setIncome(role.getIncome());

        return roleDb;
    }

    public RoleEntity createRole(RoleDto role) throws Exception {
        log.info("createRole() called with data {}: ", role);

        RoleEntity featureDb = this.validatePayloadAndReturnEntity(null, role);

        RoleEntity createdFeature = service.createOrUpdateRole(featureDb);

        return service.getRoleById(createdFeature.getId());
    }

    public RoleEntity updateRoleById(Integer roleId, RoleDto role) throws Exception {
        log.info("updateRoleById() called with id: {}", roleId);

        RoleEntity roleDb = this.validatePayloadAndReturnEntity(roleId, role);

        RoleEntity createdFeature = service.createOrUpdateRole(roleDb);

        // go to db and get all objects
        return service.getRoleById(createdFeature.getId());
    }

    public Integer deleteRoleById(Integer id) throws Exception {
        log.info("deleteRoleById() called with id: {}", id);
        return service.deleteRoleById(id);
    }

}
