package com.example.kit4_api.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Primary
public class InMemoryGameDao implements GameDao {

private final Map<String, Game> games = new HashMap<>();

    @Override
    public Collection<Game> findAll() {
        return games.values();
    }

    @Override
    public Optional<Game> findById(UUID gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    @Override
    public Game upsert(Game game) {
        games.put(String.valueOf(game.getId()), game);
        return game;
    }

    @Override
    public void delete(UUID gameId) {
        games.remove(gameId);
    }
}
