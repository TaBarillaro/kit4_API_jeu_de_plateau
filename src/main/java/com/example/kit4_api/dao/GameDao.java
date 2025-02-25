package com.example.kit4_api.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public interface GameDao {
    @NotNull Collection<Game> findAll();

}
