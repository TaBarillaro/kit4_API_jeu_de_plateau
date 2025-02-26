package com.example.kit4_api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

// details de jeu en general
public record TypeDto(UUID gameId,
                      @NotNull String gameType,
                      @NotNull int boardSize,
                      int playerCount, UUID opponentId) {


}

