package com.example.kit4_api.service;

import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.dto.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceCancellare {

    @Autowired
    private GameCatalog gameCatalog;

//    @Autowired
//    private GameFactory gameFactory;
    private static final List<GameDto> games = new ArrayList<>();


    private GameSession gameSession;

    public void setCurrentPlayerId(UUID playerId) {
        gameSession = new GameSession(playerId);
    }

    public UUID getCurrentPlayerId() {
        if (gameSession == null) {
            throw new IllegalStateException("Le joueur n'a pas été déclaré");
        }
        return gameSession.getCurrentPlayerId();
    }


    public Game createGame(UUID userId, TypeDto typeDto) {
            GameFactory gameFactory;

            switch (typeDto.gameType()) {
                case "TicTacToe":
                    gameFactory = new TicTacToeGameFactory();
                    break;
                case "ConnectFour":
                gameFactory = new ConnectFourGameFactory();
                break;
                case "Taquin":
                    gameFactory = new TaquinGameFactory();
                    break;
                default:
                    throw new IllegalStateException("Unsupported game type");
            }
            return gameFactory.createGame(typeDto.playerCount(), typeDto.boardSize());
    }

    private boolean isValidGameId(String gameId) {
        // Récupère les ID des jeux disponibles dans la bibliothèque de jeux
        Collection<String> validGameIds = gameCatalog.getGameIdentifier();

        // Vérifier si l’ID du jeu existe parmi les valides
        return validGameIds.contains(gameId);
    }


//    static {
//        // je crée des fauses parties
//        games.add(new GameDto("Harry_Potter", 8, 6, false)); // partie en cours
//        games.add(new GameDto("ticTacToe", 3, 3, true)); // partie fini
//    }
/*
    public List<GameDto> getAllGamesForUsers(String userId) {
        return games.stream()
                .filter(game -> game.getPlayers().contains(userId))
                .collect(Collectors.toList());
    }

 */

    public GameDto getGame(String gameId) {
        return games.stream().filter(gameDto -> gameDto.gameId().equals(gameId)).findFirst().orElse(null);
    }

//    public List<MoveDto> calculatePossiblesMoves(GameDto game) {
//        List<MoveDto> moves = new ArrayList<>();
//
//        GameDto gameInstance = game;
//    }

    // methode pour jouer un coup
//    public GameDto makeMove(String gameId, MoveDto moveDto, String userId) {
//        GameDto game = getGame(gameId);
//        if(game == null) {
//            return null; // jeu ne pas trouvé
//        }
//
//    }
}
