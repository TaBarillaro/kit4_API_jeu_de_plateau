package com.example.kit4_api.dto;

import java.util.Set;
import java.util.UUID;

// details de la partie
public record GameDto(String gameId, String gameType, Set<UUID> playersIds, UUID currentPlayerId) {

}
