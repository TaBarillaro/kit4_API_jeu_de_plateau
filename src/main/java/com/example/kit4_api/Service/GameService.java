package com.example.kit4_api.Service;

import com.example.kit4_api.GameCatalog;
import com.example.kit4_api.GameDto;
import com.example.kit4_api.Repository.GameRepository;
import com.example.kit4_api.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameCatalog gameCatalog;

    @Autowired
    private GameFactory gameFactory;

    // instance du repository pour gérer les données
    @Autowired
    private GameRepository gameRepository;

    public GameDto createGame(TypeDto typeDto) {
        String gameId = typeDto.gameId();
        String title = typeDto.title();

        // on crée une partie dans le moteur de jeu
        TicTacToeGame game = gameFactory.createGame(2, 3);

        GameDto gameDto = new GameDto(gameId, title, game.getId());
        return gameDto;
    }
}
