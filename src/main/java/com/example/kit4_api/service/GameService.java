package com.example.kit4_api.service;

import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.dto.TypeDto;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface GameService {
    ArrayList<Game> createGame(UUID userId, TypeDto typeDto);
    List<Game> getGamesByStatusOnGoing(UUID userId);
}
