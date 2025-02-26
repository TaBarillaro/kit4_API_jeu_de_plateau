package com.example.kit4_api.service;

import com.example.kit4_api.dao.GameCatalogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameCatalogImpl implements GameCatalog {

    @Autowired
    private GameCatalogDao gameCatalogDao;

    @Override
    public Collection<String> getGameIdentifier() {

        return gameCatalogDao.getAllGameCatalog();
    }
}
