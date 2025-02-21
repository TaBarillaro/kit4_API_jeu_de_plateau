package com.example.kit4_api.controller;

import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.dto.GameFilterDto;
import com.example.kit4_api.service.GameService;
import com.example.kit4_api.dto.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/games")
public class GameController {

    // instanciation du service pour gérer la logique de jeu
    @Autowired
    private GameService gameService;

    // methode pour créer une partie
    @PostMapping("/")
    public ResponseEntity<GameDto> createGame(@RequestBody TypeDto typeDto) {
        GameDto result = gameService.createGame(typeDto);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            // 201 created
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
    }

    // methode pour afficher l'hystorique
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames(GameFilterDto filter) {
        // liste des parties
        List<GameDto> allGames = gameService.getAllGames();

        List<GameDto> filteredGames = allGames;
        if (filter.ended() != null) {
            filteredGames = allGames.stream().filter(game -> Boolean.valueOf(game.ended()) == filter.ended()).collect(Collectors.toList());
        }

        return ResponseEntity.ok(filteredGames);
    }
}
