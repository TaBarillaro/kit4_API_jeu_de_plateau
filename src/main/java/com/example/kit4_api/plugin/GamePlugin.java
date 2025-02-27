package com.example.kit4_api.plugin;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;
import java.util.Optional;

public interface GamePlugin {

    String getName(Locale locale);
    Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize);
}
