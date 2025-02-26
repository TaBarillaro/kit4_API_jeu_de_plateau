package com.example.kit4_api.controller;

import com.example.kit4_api.dto.*;
import com.example.kit4_api.service.GameServiceImpl;
import com.example.kit4_api.service.UserServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/games")
public class GameController {

    // instanciation du service pour gérer la logique de jeu
    @Autowired
    private GameServiceImpl gameService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    // methode pour créer une partie
    @PostMapping
    public ArrayList<Game> createGame(@RequestHeader("X-UserId") UUID userId, @RequestBody TypeDto request) {

        if(!userServiceImpl.getUserById(userId)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            throw new IllegalArgumentException("User not found");
        }

        UUID opponent = request.opponentId();

        Set<UUID> playerIds = new HashSet<>();
        playerIds.add(userId);

        return gameService.createGame(userId, request);
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<Game>> getAllGamesForUser(@RequestHeader("X-UserId") String userId) {

            List<Game> allGamesForUser = gameService.getGamesByStatusOnGoing(userId);

        return ResponseEntity.ok(allGamesForUser);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable("gameId") String gameId) {
        Game currentGame = gameService.getGameById(gameId);
        return ResponseEntity.ok(currentGame);
    }

}
