package com.example.kit4_api.service;

import com.example.kit4_api.dto.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface GameService {
    Collection<String> getGameIdentifier();

    ArrayList<Game> createGame(UUID userId, TypeDto typeDto);
    List<Game> getGamesByStatusOnGoing(UUID userId);
    Game getGameById(UUID gameId);

}
