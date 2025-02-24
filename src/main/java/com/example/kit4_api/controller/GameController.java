package com.example.kit4_api.controller;

import com.example.kit4_api.dto.*;
import com.example.kit4_api.service.GameService;
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

    // methode pour afficher une partie
    @GetMapping("/{gameId}")
    public ResponseEntity<SingleGameDto> getGame(@PathVariable String gameId) {
        GameDto game = gameService.getGame(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        SingleGameDto singleGameDto = new SingleGameDto(game.gameId());
        return ResponseEntity.ok(singleGameDto);
    }

    // methode pour afficher les coups possibles
//    @GetMapping("/{gameId}/moves")
//    public ResponseEntity<MovesDto> getPossibleMoves(@PathVariable String gameId) {
//        GameDto game = gameService.getGame(gameId);
//        if (game == null) {
//            return ResponseEntity.notFound().build();
//        }
//        List<MoveDto> possibleMoves = gameService.calculatePossiblesMoves(game);
//
//        MovesDto movesDto = new MovesDto(possibleMoves);
//        return ResponseEntity.ok(movesDto);
//    }

    // methode pour jouer un coup
//    @PutMapping("/{gameId}/moves")
//    public ResponseEntity<GameDto> makeMove(@PathVariable String gameId, @RequestBody MoveDto moveDto) {
//        try {
//
//            GameDto updatedGame = gameService.makeMove(gameId, moveDto);
//
//            if (updatedGame != null) {
//                return ResponseEntity.ok(updatedGame);
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
}
