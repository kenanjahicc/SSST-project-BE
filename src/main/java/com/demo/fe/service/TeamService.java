package com.demo.fe.service;

import com.demo.fe.data.entity.EmployeeEntity;
import com.demo.fe.data.entity.TeamEntity;
import com.demo.fe.data.repository.EmployeeRepository;
import com.demo.fe.data.repository.TeamRepository;
import com.demo.fe.model.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<TeamDto> getTeam() {
        List<TeamEntity> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = new ArrayList<>();
        for (TeamEntity teamEntity : teams) {
            TeamDto teamDto = new TeamDto(teamEntity.getTitle(), teamEntity.getEmployee(),
                    teamEntity.getGained(), teamEntity.getLost(), teamEntity.getProfit());
            teamDtos.add(teamDto);
        }
        return teamDtos;
    }

    public TeamDto getTeamById(Integer id) throws Exception {
        Optional<TeamEntity> teamEntityOptional = teamRepository.findById(id);
        if (teamEntityOptional.isPresent()) {
            TeamEntity teamEntity = teamEntityOptional.get();
            return new TeamDto(teamEntity.getTitle(), teamEntity.getEmployee(),
                    teamEntity.getGained(), teamEntity.getLost(), teamEntity.getProfit());
        } else {
            throw new Exception("Team not found with id: " + id);
        }
    }

    public TeamDto createTeam(TeamDto teamDto) throws Exception {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTitle(teamDto.getTitle());
        teamEntity.setEmployee(teamDto.getEmployee());
        teamEntity.setGained(teamDto.getGained());
        teamEntity.setLost(teamDto.getLost());
        teamEntity.setProfit(teamDto.getProfit());
        teamRepository.save(teamEntity);
        return new TeamDto(teamEntity.getTitle(), teamEntity.getEmployee(),
                teamEntity.getGained(), teamEntity.getLost(), teamEntity.getProfit());
    }

    public TeamDto updateTeamById(Integer id, TeamDto teamDto) throws Exception {
        Optional<TeamEntity> teamEntityOptional = teamRepository.findById(id);
        if (teamEntityOptional.isPresent()) {
            TeamEntity teamEntity = teamEntityOptional.get();
            teamEntity.setTitle(teamDto.getTitle());
            teamEntity.setEmployee(teamDto.getEmployee());
            teamEntity.setGained(teamDto.getGained());
            teamEntity.setLost(teamDto.getLost());
            teamEntity.setProfit(teamDto.getProfit());
            teamRepository.save(teamEntity);
            return new TeamDto(teamEntity.getTitle(), teamEntity.getEmployee(),
                    teamEntity.getGained(), teamEntity.getLost(), teamEntity.getProfit());
        } else {
            throw new Exception("Team not found with id: " + id);
        }
    }

    public String deleteTeamById(Integer id) throws Exception {
        Optional<TeamEntity> teamEntityOptional = teamRepository.findById(id);
        if (teamEntityOptional.isPresent()) {
            TeamEntity teamEntity = teamEntityOptional.get();
            EmployeeEntity employeeEntity = teamEntity.getEmployee();
            if (employeeEntity != null) {
                employeeEntity.setTeam(null);
                employeeRepository.save(employeeEntity);
            }
            teamRepository.deleteById(id);
            return "Team deleted successfully";
        } else {
            throw new Exception("Team not found with id: " + id);
        }
    }
}
