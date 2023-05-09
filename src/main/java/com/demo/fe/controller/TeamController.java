package com.demo.fe.controller;

import com.demo.fe.model.EmployeeDto;
import com.demo.fe.model.ErrorObject;
import com.demo.fe.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/team")
@RestController
@Slf4j
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("")
    ResponseEntity<List<Team>> getTeamList(){
        log.info("getTeamList() called");
        return new ResponseEntity<>(teamService.getTeam(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getTeamById(@PathVariable Integer id) {
        log.info("getTeamById() called");
        try {
            return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createTeam(@RequestBody TeamDto teamDto) {
        log.info("createEmployee() called");
        try {
            return new ResponseEntity<>(teamService.createTeam(teamDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateTeamById(@PathVariable Integer id,
                                              @RequestBody TeamDto teamDto) {
        log.info("updateTeamById() called");
        try {
            return new ResponseEntity<>(teamService.updateTeamById(id, teamDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteTeamById(@PathVariable Integer id) {
        log.info("deleteTeambyId() called");
        try {
            return new ResponseEntity<>(teamService.deleteTeamById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
