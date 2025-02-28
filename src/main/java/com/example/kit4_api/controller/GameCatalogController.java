package com.example.kit4_api.controller;

import com.example.kit4_api.dto.GameInfoDto;
import com.example.kit4_api.plugin.GamePlugin;
import com.example.kit4_api.service.GameCatalog;
import com.example.kit4_api.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;
    @Autowired
    private GameServiceImpl gameServiceImpl;

//    private List<GamePlugin> gamePlugins;
//
//    public GameCatalogController(List<GamePlugin> gamePlugins) {
//        this.gamePlugins = gamePlugins;
//    }

//    @GetMapping("/info")
//    public ResponseEntity<List<GameInfoDto>> getGameInfo(@RequestHeader("Accept-Language") String language) {
//        // on met la langue demandé dans le header
//        Locale locale = Locale.forLanguageTag(language);
//
//        // on crée une liste de GameInfoDto pour retourner les infos sur les jeux
//        List<GameInfoDto> gameInfoList = gamePlugins.stream()
//                .map(plugin -> new GameInfoDto(plugin.getName(locale)))  // on utilise le plugin pour retrouver la lingue correcte
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(gameInfoList);
//    }

    @GetMapping("/identifiers")
    public Collection<String> getGameIdentifiers(@RequestHeader("Accept-Language") Locale locale) {

        return gameServiceImpl.getGameIdentifier();
    }


}
