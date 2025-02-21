package com.example.kit4_api.service;

import com.example.kit4_api.GameCatalog;
import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.repository.GameRepository;
import com.example.kit4_api.dto.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameCatalog gameCatalog;

//    @Autowired
//    private GameFactory gameFactory;

    // instance du repository pour gérer les données
    @Autowired
    private GameRepository gameRepository;

    public GameDto createGame(TypeDto typeDto) {
        String gameId = typeDto.gameId();

        GameDto gameDto = new GameDto(typeDto.gameId(), typeDto.width(), typeDto.height(), typeDto.ended());
        return gameDto;
    }

    // fausse liste de partie pour les tests
    private static final List<GameDto> games = new ArrayList<>();

    static {
        // je crée des fauses parties
        games.add(new GameDto("Harry_Potter", 8, 6, false)); // partie en cours
        games.add(new GameDto("ticTacToe", 3, 3, true)); // partie fini
    }

    public List<GameDto> getAllGames() {
        return games;
    }

    public GameDto getGame(String gameId) {
        return games.stream().filter(gameDto -> gameDto.gameId().equals(gameId)).findFirst().orElse(null);
    }
}
