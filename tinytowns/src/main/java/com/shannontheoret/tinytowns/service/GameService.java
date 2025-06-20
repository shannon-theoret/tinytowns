package com.shannontheoret.tinytowns.service;

import com.shannontheoret.tinytowns.*;
import com.shannontheoret.tinytowns.dto.AddPlayerResponseDto;
import com.shannontheoret.tinytowns.dto.GameDto;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.exceptions.GameCodeNotFoundException;
import com.shannontheoret.tinytowns.exceptions.InternalGameException;
import com.shannontheoret.tinytowns.exceptions.InvalidMoveException;

import java.util.List;
import java.util.Set;


public interface GameService {

    List<JPAGame> findAll(); // Or List<GameDto> if this is exposed to frontend

    GameDto findByCode(String code) throws GameCodeNotFoundException;

    void save(JPAGame game);

    GameDto newGame();

    AddPlayerResponseDto addPlayer(String gameCode, String playerName)
            throws InvalidMoveException, GameCodeNotFoundException;

    GameDto startGame(String gameCode)
            throws InvalidMoveException, GameCodeNotFoundException;

    GameDto namePiece(String gameCode, Piece piece)
            throws InvalidMoveException, GameCodeNotFoundException;

    GameDto placePiece(String gameCode, Long playerId, Integer gridIndex)
            throws InvalidMoveException, GameCodeNotFoundException, InternalGameException;

    GameDto build(String gameCode, Long playerId, Integer gridIndex, Set<Integer> indexes, BuildingName building)
            throws InvalidMoveException, GameCodeNotFoundException;

    GameDto endTurn(String gameCode)
            throws GameCodeNotFoundException, InvalidMoveException, InternalGameException;
}