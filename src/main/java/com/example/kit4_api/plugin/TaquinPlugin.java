package com.example.kit4_api.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class TaquinPlugin implements GamePlugin{

    private final TaquinGameFactory taquinGameFactory;
    private MessageSource messageSource;

    public TaquinPlugin(MessageSource messageSource) {
        this.taquinGameFactory = new TaquinGameFactory();
        this.messageSource = messageSource;
    }

    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.name", null, locale);
    }

    @Override
    public Game createGame(Optional<Integer> playerCount, Optional<Integer> boardSize) {
        return taquinGameFactory.createGame(
                playerCount.orElse(defaultPlayerCount),
                boardSize.orElse(defaultBoardSize)
        );
    }
}
