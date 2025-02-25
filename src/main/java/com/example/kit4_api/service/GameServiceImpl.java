package com.example.kit4_api.service;

import com.example.kit4_api.dao.GameDao;
import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.dto.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;
    private final ArrayList<Game> games = new ArrayList<>();

    public ArrayList<Game> createGame(UUID userId, TypeDto typeDto) {
        GameFactory gameFactory;

        Set<UUID> playersIds = new HashSet<>();
        playersIds.add(userId);
        Game game = null;

        switch (typeDto.gameType()) {
            case "TicTacToe":
                playersIds.add(typeDto.opponentId());
                gameFactory = new TicTacToeGameFactory();
                game = gameFactory.createGame(typeDto.boardSize(), playersIds);
                break;
            case "connectFour":
                playersIds.add(typeDto.opponentId());
                gameFactory = new ConnectFourGameFactory();
                game = gameFactory.createGame(typeDto.boardSize(), playersIds);
                break;
            case "15 puzzle":
                gameFactory = new TaquinGameFactory();
                game = gameFactory.createGame(typeDto.boardSize(), playersIds);
                break;
            default:
                throw new IllegalStateException("Unsupported game type");
        }
        if (game != null) {
            games.add(game);
        }
//        return gameFactory.createGame(typeDto.playerCount(), typeDto.boardSize());
        return games;

    }



    @Override
    public List<Game> getGamesByStatusOnGoing(UUID userId) {
        return games
                .stream()
                .filter(game -> game.getStatus().equals(GameStatus.ONGOING)).collect(Collectors.toList());
    }

//    @Override
//    public List<GameDto> getGamesByStatus(GameStatus gameStatus, UUID userId) {
//        return gameDao.findAll()
//                .stream()
//                .filter(game -> gameStatus.equals(game.getStatus()) && game.getPlayerIds().contains(userId))
//                        .map(game -> new GameDto(game.getId().toString(), game.getFactoryId(), game.getPlayerIds(), game.getCurrentPlayerId()))
//                .collect(Collectors.toList());
//    }
}
