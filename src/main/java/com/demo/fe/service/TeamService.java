package com.demo.fe.service;

import com.demo.fe.model.TeamDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeamService {

    private Map<Integer, TeamDto> teams = new HashMap<>();
    private Integer lastTeamId = 0;

    public TeamDto createTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setId(lastTeamId++);
        team.setName(teamDto.getName());
        team.setMonthlyIncome(teamDto.getMonthlyIncome());
        teams.put(teamDto.getId(), team);
        return teamDto;
    }

    public TeamDto getTeam(Integer id) {
        TeamDto team = teams.get(id);
        if(team == null) {
            return null;
        }
    }
}
