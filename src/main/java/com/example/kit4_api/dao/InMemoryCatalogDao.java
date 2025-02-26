package com.example.kit4_api.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCatalogDao implements GameCatalogDao{

    @Override
    public List<String> getAllGameCatalog() {
        List<String> gamesCatalog = new ArrayList<String>();
        gamesCatalog.add("TicTacToe");
        gamesCatalog.add("Taquin");
        gamesCatalog.add("ConnectFour");
        return gamesCatalog;
    }
}
