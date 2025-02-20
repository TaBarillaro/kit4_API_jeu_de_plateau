package com.example.kit4_api.Controller;

import com.example.kit4_api.GameCatalog;
import com.example.kit4_api.GameDto;
import com.example.kit4_api.Service.GameService;
import com.example.kit4_api.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    // instanciation du service pour gérer la logique de jeu
    @Autowired
    private GameService gameService;

    @PostMapping("/")
    public GameDto createGame(@RequestBody TypeDto typeDto) {
        GameDto result = gameService.createGame(typeDto);
        return result;
    }

}
