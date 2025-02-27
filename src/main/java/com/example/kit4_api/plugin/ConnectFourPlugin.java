package com.example.kit4_api.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class ConnectFourPlugin implements GamePlugin {

    private final ConnectFourGameFactory connectFourGameFactory;
    private final MessageSource messageSource;

    public ConnectFourPlugin(MessageSource messageSource) {
        this.connectFourGameFactory = new ConnectFourGameFactory();
        this.messageSource = messageSource;
    }

    @Value("${game.connectfour.default-player-count:2}")
    private int defaultPlayerCount;

    @Value("${game.connectfour.default-board-size:7}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connectfour.name", null, locale);
    }

    @Override
    public Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize) {
        return connectFourGameFactory.createGame(
                playerCount.orElse(defaultPlayerCount),
                boardSize.orElse(defaultBoardSize)
        );
    }
}
