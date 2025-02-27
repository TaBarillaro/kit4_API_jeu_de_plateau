package com.example.kit4_api.service;

import com.example.kit4_api.dao.InMemoryGameDao;
import com.example.kit4_api.dto.TypeDto;
import com.example.kit4_api.plugin.GamePlugin;
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
    private InMemoryGameDao gameDao;

    private final List<GamePlugin> gamePlugins;

    public GameServiceImpl(List<GamePlugin> gamePlugins) {
        this.gamePlugins = gamePlugins;
    }

//    private final ArrayList<Game> games = new ArrayList<>();


    public ArrayList<Game> createGame(UUID userId, TypeDto typeDto) {
//        GameFactory gameFactory;

        Set<UUID> playersIds = new HashSet<>();
        playersIds.add(userId);
        Game game = null;

        for (GamePlugin plugin : gamePlugins) {
            if (plugin.getName(Locale.ENGLISH).equalsIgnoreCase(typeDto.gameType())) {
                game = plugin.createGame(Optional.of(playersIds.size()),  Optional.ofNullable(typeDto.boardSize()));
                break;
            };
        }
        if (game == null) {
            throw new IllegalStateException("No games found");
        }
        gameDao.upsert(game);

        return new ArrayList<>(gameDao.findAll());

//        switch (typeDto.gameType()) {
//            case "TicTacToe":
//                playersIds.add(typeDto.opponentId());
//                gameFactory = new TicTacToeGameFactory();
//                game = gameFactory.createGame(typeDto.boardSize(), playersIds);
//                break;
//            case "ConnectFour":
//                playersIds.add(typeDto.opponentId());
//                gameFactory = new ConnectFourGameFactory();
//                game = gameFactory.createGame(typeDto.boardSize(), playersIds);
//                break;
//            case "15 puzzle":
//                gameFactory = new TaquinGameFactory();
//                game = gameFactory.createGame(typeDto.boardSize(), playersIds);
//                break;
//            default:
//                throw new IllegalStateException("Unsupported game type");
//        }
//        if (game != null) {
//            gameDao.upsert(game);
//        }
//        return new ArrayList<>(gameDao.findAll());
    }


    @Override
    public List<Game> getGamesByStatusOnGoing(UUID userId) {
//        return games
        return gameDao.findAll()
                .stream()
                .filter(game -> game.getStatus().equals(GameStatus.ONGOING)).collect(Collectors.toList());
    }

    @Override
    public Game getGameById(UUID gameId) {
//        return games
        return gameDao.findById(gameId).orElseThrow(() -> new IllegalStateException("Game not found or you are not the current player"));
//                .stream()
//                .filter(game -> game.getId().equals(gameId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("Game not found or you are not the current player"));
    }

}
