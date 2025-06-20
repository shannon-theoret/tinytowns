package com.shannontheoret.tinytowns.controller;

import com.shannontheoret.tinytowns.*;
import com.shannontheoret.tinytowns.dto.AddPlayerResponseDto;
import com.shannontheoret.tinytowns.dto.GameDto;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.exceptions.GameCodeNotFoundException;
import com.shannontheoret.tinytowns.exceptions.InternalGameException;
import com.shannontheoret.tinytowns.exceptions.InvalidMoveException;
import com.shannontheoret.tinytowns.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(
        origins = {"https://tinytowns.shannontheoret.com", "http://localhost:5173"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowCredentials = "true"
)
@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{gameCode}")
    public GameDto getGame(@PathVariable("gameCode") String gameCode) throws GameCodeNotFoundException {
        return gameService.findByCode(gameCode);
    }

    @PostMapping("newGame")
    public GameDto newGame() {
        return gameService.newGame();
    }

    @PostMapping("{gameCode}/addPlayer")
    public AddPlayerResponseDto addPlayer(@PathVariable("gameCode") String gameCode,
                                          @RequestParam String playerName)
            throws InvalidMoveException, GameCodeNotFoundException {
        return gameService.addPlayer(gameCode, playerName);
    }

    @PostMapping("{gameCode}/startGame")
    public GameDto startGame(@PathVariable("gameCode") String gameCode)
            throws InvalidMoveException, GameCodeNotFoundException {
        return gameService.startGame(gameCode);
    }

    @PostMapping("{gameCode}/namePiece")
    public GameDto namePiece(@PathVariable("gameCode") String gameCode,
                             @RequestParam Piece piece)
            throws InvalidMoveException, GameCodeNotFoundException {
        return gameService.namePiece(gameCode, piece);
    }

    @PostMapping("{gameCode}/placePiece")
    public GameDto placePiece(@PathVariable("gameCode") String gameCode,
                              @RequestParam Long playerId,
                              @RequestParam Integer gridIndex)
            throws GameCodeNotFoundException, InvalidMoveException, InternalGameException {
        return gameService.placePiece(gameCode, playerId, gridIndex);
    }

    @PostMapping("{gameCode}/buildPiece")
    public GameDto build(@PathVariable("gameCode") String gameCode,
                         @RequestParam Long playerId,
                         @RequestParam Integer gridIndex,
                         @RequestBody Set<Integer> indexes,
                         @RequestParam BuildingName building)
            throws GameCodeNotFoundException, InvalidMoveException, InternalGameException {
        return gameService.build(gameCode, playerId, gridIndex, indexes, building);
    }

    @PostMapping("{gameCode}/endTurn")
    public GameDto endTurn(@PathVariable("gameCode") String gameCode)
            throws InternalGameException, GameCodeNotFoundException, InvalidMoveException {
        return gameService.endTurn(gameCode);
    }
}