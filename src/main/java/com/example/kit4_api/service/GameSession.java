package com.example.kit4_api.service;

import java.util.UUID;

public class GameSession {
    private UUID currentPlayerId;

    // Constructeur
    public GameSession(UUID currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public UUID getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(UUID currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }
}
