package com.example.kit4_api.controller;

import com.example.kit4_api.dto.*;
import com.example.kit4_api.service.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/games")
public class GameController {

    // instanciation du service pour gérer la logique de jeu
    @Autowired
    private GameServiceImpl gameService;

    // methode pour créer une partie
    @PostMapping
    public ResponseEntity<ArrayList<Game>> createGame(@RequestHeader("X-UserId") UUID userId, @RequestBody TypeDto typeDto) {

        // on crée le jeu
        ArrayList<Game> result = gameService.createGame(userId, typeDto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            // 201 created
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<Game>> getAllGamesForUser(@RequestHeader("X-UserId") UUID userId) {

            List<Game> allGamesForUser = gameService.getGamesByStatusOnGoing(userId);

        return ResponseEntity.ok(allGamesForUser);
    }

}
