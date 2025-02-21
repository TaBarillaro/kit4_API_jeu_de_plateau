package com.example.kit4_api.service;

import com.example.kit4_api.GameCatalog;
import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.repository.GameRepository;
import com.example.kit4_api.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameCatalog gameCatalog;

//    @Autowired
//    private GameFactory gameFactory;

    // instance du repository pour gérer les données
    @Autowired
    private GameRepository gameRepository;

    public GameDto createGame(TypeDto typeDto) {
        String gameId = typeDto.gameId();

        GameDto gameDto = new GameDto(typeDto.gameId(), typeDto.width(), typeDto.height());
        return gameDto;
    }
}
