package com.example.kit4_api.controller;

import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.service.GameService;
import com.example.kit4_api.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/games")
public class GameController {

    // instanciation du service pour gérer la logique de jeu
    @Autowired
    private GameService gameService;

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

}
