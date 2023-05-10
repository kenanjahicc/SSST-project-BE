package com.demo.fe.service;


import com.demo.fe.data.entity.TeamEntity;
import com.demo.fe.data.service.TeamDataService;
import com.demo.fe.model.TeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TeamService {
    @Autowired
    TeamDataService teamDataService;

    public List<TeamEntity> getTeamList() {
        log.info("getTeamList() called");
        return teamDataService.getTeamList();
    }

    public TeamEntity getTeamById(Integer id) throws Exception {
        log.info("getTeamById() called with id: {}", id);
        return teamDataService.getTeamById(id);
    }

    public TeamEntity validatePayloadAndReturnEntity(Integer teamId, TeamDto team) throws Exception {
        Objects.requireNonNull(team.getTitle(), "Team title is required");
        if (team.getTitle().isEmpty()){
            log.info("Team title is required");
            throw new Exception("Team title is required");
        }

        // validation
        // 1. update
        if (teamId != null) {
            TeamEntity existingEntity = teamDataService.getTeamById(teamId);
            if (existingEntity == null) {
                log.info("Team with id {} does not exist.", teamId);
                throw new Exception(String.format("Team with id '%s' does not exist.", teamId));
            }
            if(!Objects.equals(existingEntity.getTitle(), team.getTitle())) {
                if (teamDataService.getTeamByTitle(team.getTitle()) != null){
                    log.info("Team with title {} already exists. It is not possible to update it.", team.getTitle());
                    throw new Exception(String.format("Team with title '%s' already exists. It is not possible to update it.", team.getTitle()));
                }
            }
        } else { // 2. insert
            // in a case of insert (currencyId is null) check if name already exists
            if (teamDataService.getTeamByTitle(team.getTitle()) != null){
                log.info("Team with title {} already exists. It is not possible to update it.", team.getTitle());
                throw new Exception(String.format("Team with title '%s' already exists. It is not possible to update it.", team.getTitle()));
            }

        }

        TeamEntity teamDb = new TeamEntity();
        // in case of insert teamId will be created on repository level
        if (teamId != null) {
            teamDb.setId(teamId);
        }
        teamDb.setTitle(team.getTitle());

        return teamDb;
    }

    public TeamEntity createTeam(TeamDto team) throws Exception {
        log.info("createTeam() called with data {}: ", team);

        TeamEntity teamDb = this.validatePayloadAndReturnEntity(null, team);

        TeamEntity createdTeam = teamDataService.createOrUpdateTeam(teamDb);

        return teamDataService.getTeamById(createdTeam.getId());
    }

    public TeamEntity updateTeamById(Integer teamId, TeamDto team) throws Exception {
        log.info("updateTeamById() called with id: {}", teamId);

        TeamEntity teamDb = this.validatePayloadAndReturnEntity(teamId, team);

        TeamEntity createdTeam = teamDataService.createOrUpdateTeam(teamDb);

        // go to db and get all objects
        return teamDataService.getTeamById(createdTeam.getId());
    }

    public Integer deleteTeamById(Integer id) throws Exception {
        log.info("deleteTeamById() called with id: {}", id);
        return teamDataService.deleteTeamById(id);
    }
}
