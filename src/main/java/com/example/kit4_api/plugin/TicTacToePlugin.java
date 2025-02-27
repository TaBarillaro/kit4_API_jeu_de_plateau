package com.example.kit4_api.plugin;

import com.sun.source.util.Plugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class TicTacToePlugin implements GamePlugin {

    private final TicTacToeGameFactory gameFactory;
    private final MessageSource messageSource;

    // constructeur
    public TicTacToePlugin(MessageSource messageSource) {
        this.gameFactory = new TicTacToeGameFactory();
        this.messageSource = messageSource;
    }

    @Value("${game.tictactoe.default-player-count:2}")
    private int defaultPlayerCount;

    @Value("${game.tictactoe.default-board-size:3}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.tictactoe.name", null, locale);
    }

    @Override
    public Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize) {
        return gameFactory.createGame(
                playerCount.orElse(defaultPlayerCount),
                boardSize.orElse(defaultBoardSize)
        );
    }
}
