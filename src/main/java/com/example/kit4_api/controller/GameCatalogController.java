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


    @GetMapping("/identifiers")
    public Collection<String> getGameIdentifiers(@RequestHeader("Accept-Language") Locale locale) {

        return gameServiceImpl.getGameIdentifier(locale);
    }


}
