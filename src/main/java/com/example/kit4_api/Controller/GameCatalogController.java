package com.example.kit4_api.Controller;

import com.example.kit4_api.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/games")
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/identifiers")
    public Collection<String> getGameIdentifiers() {
        return gameCatalog.getGameIdentifier();
    }
}
