package com.shannontheoret.tinytowns.service;

import com.shannontheoret.tinytowns.*;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.exceptions.GameCodeNotFoundException;
import com.shannontheoret.tinytowns.exceptions.InternalGameException;
import com.shannontheoret.tinytowns.exceptions.InvalidMoveException;

import java.util.List;
import java.util.Set;

public interface GameService {
    List<JPAGame> findAll();

    JPAGame findByCode(String code) throws GameCodeNotFoundException;

    void save(JPAGame game);

    JPAGame startGame(String gameCode) throws InvalidMoveException, GameCodeNotFoundException;

    JPAGame newGame();

    JPAGame addPlayer(String gameCode, String playerName) throws InvalidMoveException, GameCodeNotFoundException;

    JPAGame namePiece(String gameCode, Piece piece) throws InvalidMoveException, GameCodeNotFoundException;

    JPAGame placePiece(String gameCode, Long playerId, Integer gridIndex) throws  InvalidMoveException, GameCodeNotFoundException, InternalGameException;

    JPAGame build(String gameCode, Long playerId, Integer gridIndex, Set<Integer> indexes, BuildingName building) throws InvalidMoveException, GameCodeNotFoundException, InternalGameException;

    JPAGame endTurn(String gameCode) throws GameCodeNotFoundException, InvalidMoveException, InternalGameException;
}
