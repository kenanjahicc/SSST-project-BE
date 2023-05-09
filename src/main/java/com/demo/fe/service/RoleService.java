package com.demo.fe.service;

import com.demo.fe.data.entity.RoleEntity;
import com.demo.fe.data.repository.RoleRepository;
import com.demo.fe.model.RoleDto;

import java.util.ArrayList;
import java.util.List;

public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto createRole(RoleDto roleDto) {
        RoleEntity role = new RoleEntity();
        role.setTitle(roleDto.getTitle());
        role.setDescription(roleDto.getDescription());
        role.setSalary(roleDto.getSalary());
        roleRepository.save(role);
        roleDto.setId(role.getId());
        return roleDto;
    }

    public List<RoleDto> getRoleList() {
        List<RoleEntity> roles = roleRepository.findAll();
        List<RoleDto> result = new ArrayList<>();
        for (RoleEntity role : roles) {
            result.add(new RoleDto(role.getId(),
                    role.getTitle(),
                    role.getDescription(),
                    role.getSalary()));
        }
        return result;
    }

    public RoleDto getRole(long id) {
        roleRepository.getById(id);

        return new RoleDto(id, "Designer", "Conduct user research and design the app's user interface to ensure a seamless and enjoyable user experience.", 1500.);
    }

    public RoleDto updateRole(long id, RoleDto roleDto) {
        System.out.println("Role found for a give id: " + id);
        roleDto.setId(id);
        roleDto.setTitle("DevOps");
        roleDto.setSalary(1600.);
        return roleDto;
    }

    public void deleteRole(long id) {
        System.out.println("Deleted " + id);
    }

}
