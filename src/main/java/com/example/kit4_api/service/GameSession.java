package com.example.kit4_api.service;

public class GameSession {
    private String currentPlayerId;

    // Constructeur
    public GameSession(String currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public String getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(String currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }
}
