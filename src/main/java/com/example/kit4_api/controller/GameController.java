package com.example.kit4_api.controller;

import com.example.kit4_api.dto.*;
import com.example.kit4_api.service.GameService;
import com.example.kit4_api.service.UserService;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private MessageSource messageSource;

    // instanciation du service pour gérer la logique de jeu
    private GameService gameServiceImpl;

    private UserService userServiceImpl;

    public GameController(GameService gameServiceImpl, UserService userServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    // methode pour créer une partie
    @PostMapping
    public ArrayList<Game> createGame(@RequestHeader("X-UserId") UUID userId, @RequestBody TypeDto request) {

        if(!userServiceImpl.getUserById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        return gameServiceImpl.createGame(userId, request);
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<Game>> getAllGamesForUser(@RequestHeader("X-UserId") UUID userId) {

            List<Game> allGamesForUser = gameServiceImpl.getGamesByStatusOnGoing(userId);

        return ResponseEntity.ok(allGamesForUser);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable("gameId") UUID gameId) {
        Game currentGame = gameServiceImpl.getGameById(gameId);
        return ResponseEntity.ok(currentGame);
    }

}
