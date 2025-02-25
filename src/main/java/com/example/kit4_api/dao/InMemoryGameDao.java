package com.example.kit4_api.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryGameDao implements GameDao {
    private Set<Game> games = new HashSet<>();

    @Override
    public Collection<Game> findAll() {
        return games;
    }
}
