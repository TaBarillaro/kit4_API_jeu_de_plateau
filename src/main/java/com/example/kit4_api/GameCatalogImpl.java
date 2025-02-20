package com.example.kit4_api;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogImpl implements GameCatalog {

    // instance de la classe TicTacToeGameFactory
    private TicTacToeGameFactory ticTacToeGameFactory;

    // constructeur
    public GameCatalogImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
    }

    @Override
    public Collection<String> getGameIdentifier() {
        // Créer une collection d’identificateurs de jeu
        Collection<String> gameIdentifiers = new ArrayList<>();

        // Ajouter l’identifiant pour le jeu "tic tac toe"
        // L’identifiant est obtenu par la factory
        gameIdentifiers.add(ticTacToeGameFactory.getGameFactoryId());
        return gameIdentifiers;
    }
}
