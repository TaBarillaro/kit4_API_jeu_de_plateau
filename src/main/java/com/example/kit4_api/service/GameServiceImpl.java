package com.example.kit4_api.service;

import com.example.kit4_api.dao.GameDao;
import com.example.kit4_api.dto.TypeDto;
import com.example.kit4_api.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {


    @Autowired
    private GameDao gameDao;

    @Autowired
    private List<GamePlugin> gamePlugins;

    @Override
    public Collection<String> getGameIdentifier(Locale locale) {
        return gamePlugins.stream()
                .map(gamePlugins -> gamePlugins.getName(locale))
                .collect(Collectors.toList());
    }


    public ArrayList<Game> createGame(UUID userId, TypeDto typeDto) {


        Set<UUID> playersIds = new HashSet<>();
        playersIds.add(userId);
        Game game = null;

        for (GamePlugin plugin : gamePlugins) {
            if (plugin.getName(Locale.getDefault()).equalsIgnoreCase(typeDto.gameType())) {
                game = plugin.createGame(Optional.of(playersIds.size()),  Optional.ofNullable(typeDto.boardSize()));
                break;
            };
        }
        if (game == null) {
            throw new IllegalStateException("No games found");
        }
        gameDao.upsert(game);

        return new ArrayList<>(gameDao.findAll());

    }


    @Override
    public List<Game> getGamesByStatusOnGoing(UUID userId) {

        return gameDao.findAll()
                .stream()
                .filter(game -> game.getStatus().equals(GameStatus.ONGOING)).collect(Collectors.toList());
    }

    @Override
    public Game getGameById(UUID gameId) {

        return gameDao.findById(gameId).orElseThrow(() -> new IllegalStateException("Game not found or you are not the current player"));
    }

}
