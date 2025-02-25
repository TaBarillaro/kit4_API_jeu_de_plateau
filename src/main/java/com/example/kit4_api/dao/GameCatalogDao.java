package com.example.kit4_api.dao;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface GameCatalogDao {
    List<String> getAllGameCatalog();
}
