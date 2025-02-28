package com.example.kit4_api.config;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

   @Bean
    public TicTacToeGameFactory ticTacToeGameFactory() {
       return new TicTacToeGameFactory();
   }

    @Bean
    public TaquinGameFactory taquinGameFactory() {
        return new TaquinGameFactory();
    }

    @Bean
    public ConnectFourGameFactory connectFourGameFactory() {
        return new ConnectFourGameFactory();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
