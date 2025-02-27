package com.example.kit4_api.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GameDao {
    @NotNull Collection<Game> findAll();
    Optional<Game> findById(@NotNull UUID gameId);
    @NotNull Game upsert(@NotNull Game game);
    void delete(@NotNull UUID gameId);

}
