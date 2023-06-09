package com.demo.fe.data.service;

import com.demo.fe.data.entity.TeamEntity;
import com.demo.fe.data.repository.EmployeeRepository;
import com.demo.fe.data.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TeamDataService {
    @Autowired
    TeamRepository repository;

    @Autowired
    EmployeeRepository employeeRepository;

    public TeamEntity createOrUpdateTeam(TeamEntity team) {
        return repository.save(team);
    }

    public List<TeamEntity> getTeamList() {
        log.info("getTeamList() called");
        return repository.findAll();
    }


    public TeamEntity getTeamById(Integer teamId) throws Exception {
        log.info("getTeamById called with teamId " + teamId);
        Optional<TeamEntity> result = repository.findById(teamId);
        if (result.isPresent()) {
            return result.get();
        } else {
            log.error(String.format("Could not find team with id %s", teamId));
            throw new Exception(String.format("Could not find team with id %s", teamId));
        }
    }
    public TeamEntity getTeamByTitle(String title) {
        log.info("getTeamByTitle called with title " + title);
        return repository.findOneByTitle(title);
    }


    public Integer deleteTeamById(Integer teamId) throws Exception  {
        // check whether id exists or not. If not, throw an exception
        TeamEntity team = this.getTeamById(teamId);

        if (employeeRepository.findAllByTeam(team) != null) {
            throw new Exception(String.format("Could not delete team, an employee in team with id: {} already exists!", teamId));
        }

        // delete the employee
        repository.deleteById(teamId);
        return 1;
    }
}
