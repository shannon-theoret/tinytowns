package com.shannontheoret.tinytowns.controller;

import com.shannontheoret.tinytowns.*;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.entity.JPAPlayer;
import com.shannontheoret.tinytowns.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.Set;

@RestController
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{gameCode}")
    public JPAGame getGame(@PathVariable("gameCode") String gameCode) throws GameCodeNotFoundException {
        return gameService.findByCode(gameCode);
    }

    @PostMapping("newGame")
    public JPAGame newGame() {
        return gameService.newGame();
    }

    @PostMapping("{gameCode}/addPlayer")
    public JPAGame addPlayer(@PathVariable("gameCode") String gameCode, @RequestParam String playerName) throws InvalidMoveException, GameCodeNotFoundException {
        return gameService.addPlayer(gameCode, playerName);
    }

    @RequestMapping("{gameCode}/startGame")
    public JPAGame startGame(@PathVariable("gameCode") String gameCode) throws InvalidMoveException, GameCodeNotFoundException {
        return gameService.startGame(gameCode);
    }

    @PostMapping("{gameCode}/namePiece")
    public JPAGame namePiece(@PathVariable("gameCode") String gameCode, @RequestParam Piece piece) throws InvalidMoveException, GameCodeNotFoundException {
        return gameService.namePiece(gameCode, piece);
    }

    @PostMapping("{gameCode}/placePiece")
    public JPAGame placePiece(@PathVariable("gameCode") String gameCode, @RequestParam Long playerId, @RequestParam Integer gridIndex) throws GameCodeNotFoundException, InvalidMoveException, InternalGameException {
        return gameService.placePiece(gameCode, playerId, gridIndex);
    }

    @PostMapping("{gameCode}/buildPiece")
    public JPAGame build(@PathVariable("gameCode") String gameCode, @RequestParam Long playerId, @RequestParam Integer gridIndex, @RequestBody Set<Integer> indexes, @RequestParam BuildingName building) throws GameCodeNotFoundException, InvalidMoveException, InternalGameException {
        return gameService.build(gameCode, playerId, gridIndex, indexes, building);
    }

    @PostMapping("{gameCode}/endTurn")
    public JPAGame endTurn(@PathVariable("gameCode") String gameCode, @RequestParam Long playerId) throws InternalGameException, GameCodeNotFoundException, InvalidMoveException {
        return gameService.endTurn(gameCode, playerId);
    }

}
