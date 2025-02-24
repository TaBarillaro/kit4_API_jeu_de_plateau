package com.example.kit4_api.service;

import com.example.kit4_api.GameCatalog;
import com.example.kit4_api.dto.GameDto;
import com.example.kit4_api.repository.GameRepository;
import com.example.kit4_api.dto.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameCatalog gameCatalog;

//    @Autowired
//    private GameFactory gameFactory;

    // instance du repository pour gérer les données
    @Autowired
    private GameRepository gameRepository;

    private GameSession gameSession;

    // Impostiamo il giocatore corrente
    public void setCurrentPlayerId(String playerId) {
        gameSession = new GameSession(playerId);
    }

    // Ottieni l'ID del giocatore corrente
    public String getCurrentPlayerId() {
        if (gameSession == null) {
            throw new IllegalStateException("Le joueur n'a pas été déclaré");
        }
        return gameSession.getCurrentPlayerId();
    }

    public GameDto createGame(String userId, TypeDto typeDto) {

        // Ottieni l'ID del giocatore corrente
        String currentPlayerId = getCurrentPlayerId();

        // Verifica che l'ID utente passato corrisponda a quello del giocatore corrente
        if (!userId.equals(currentPlayerId)) {
            // Se non corrisponde, restituisci null o un errore
            return null;
        }

        String gameId = typeDto.gameId();

        GameDto gameDto = new GameDto(gameId, typeDto.width(), typeDto.height(), typeDto.ended());
        return gameDto;
    }

    // fausse liste de partie pour les tests
    private static final List<GameDto> games = new ArrayList<>();

    static {
        // je crée des fauses parties
        games.add(new GameDto("Harry_Potter", 8, 6, false)); // partie en cours
        games.add(new GameDto("ticTacToe", 3, 3, true)); // partie fini
    }

    public List<GameDto> getAllGamesForUsers(String userId) {
        return games;
    }

    public GameDto getGame(String gameId) {
        return games.stream().filter(gameDto -> gameDto.gameId().equals(gameId)).findFirst().orElse(null);
    }

//    public List<MoveDto> calculatePossiblesMoves(GameDto game) {
//        List<MoveDto> moves = new ArrayList<>();
//
//        GameDto gameInstance = game;
//    }

    // methode pour jouer un coup
//    public GameDto makeMove(String gameId, MoveDto moveDto, String userId) {
//    if (!isValidUser(userId)) {
//        return null;
//    }
//        // On recuper le jeu du repository
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found"));
//
//        // on verifie si le coup est valid
//        boolean isValidMove = game.isValidMove(moveDto.row(), moveDto.col());
//
//        if (!isValidMove || game.isEnded()) {
//            si non, on reçoit null
//            return null;
//        }
//
//        game.applyMove(moveDto.row(), moveDto.col());
//
//        if (game.isEnded()) {
//            game.setEnded(true);
//        }
//
//        gameRepository.save(game);
//
//        // on reçoit le dto mis à jour
//        return new GameDto(game.getGameId(), game.getWidth(), game.getHeight(), game.isEnded());
//    }
}
