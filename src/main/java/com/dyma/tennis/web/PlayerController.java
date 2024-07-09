package com.dyma.tennis.web;

import com.dyma.tennis.PlayerList;
import com.dyma.tennis.service.PlayerService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.dyma.tennis.Player;

import java.util.Collections;
import java.util.List;
import com.dyma.tennis.HealthCheck;
import com.dyma.tennis.service.HealthCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Content;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;


@Tag(name="Tennis players API")
@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Operation(summary = "Finds players", description = "finds players")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Players list",
                    content ={@Content(mediaType = "application/json",
                            array= @ArraySchema (schema = @Schema(implementation = Player.class)))})
    })
    // En mettant en place un service, le controlleur se repose sur celui-ci pour
    // effectuer tout ce qui est logique metier
    @GetMapping
    public List<Player>list(){
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Finds players", description = "Finds player by lastName")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Players",
                    content ={@Content(mediaType = "application/json",
                          schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "404", description ="Player with specified last name not found",
                    content ={@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })

    @GetMapping("{lastName}")
    public Player getByLastName(@PathVariable("lastName") String lastName){
        return playerService.getByLastName(lastName);
    }

    @Operation(summary = "Create  players", description = "Create players ")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Players",
                    content ={@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerToRegister.class))}),
            @ApiResponse(responseCode = "404", description ="Player with specified last name not found",
                    content ={@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })
    @PostMapping
    public Player createPlayer(@RequestBody @Valid PlayerToRegister playerToRegister){
        return playerService.create(playerToRegister);
    }
    @Operation(summary = "update  players", description = "update players ")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description ="Players",
                    content ={@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))})
    })
    @PutMapping
    public Player updatePlayer(@RequestBody @Valid PlayerToRegister playerToRegister){
        return playerService.update(playerToRegister);
    }

    @Operation(summary = "delete players", description = "delete players ")
    @ApiResponses(value={
            @ApiResponse(responseCode = "204", description ="Players has been deleted")
    })
    @DeleteMapping("{lastName}")
    public void deletePlayerByLastName(@PathVariable("lastName") String lastName)
    {
        playerService.delete(lastName);
    }
}


