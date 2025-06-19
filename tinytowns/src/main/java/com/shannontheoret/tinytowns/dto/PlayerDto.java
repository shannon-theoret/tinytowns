package com.shannontheoret.tinytowns.dto;

import com.shannontheoret.tinytowns.Piece;
import com.shannontheoret.tinytowns.PlayerStep;

import java.util.Map;

public class PlayerDto {
    private Long id;
    private String name;
    private Map<Integer, Piece> squares;
    private Map<Piece, Integer> score;
    private Integer scorePenalty;
    private Integer playerOrder;
    private boolean masterBuilder;
    private boolean turnToPlace;
    private PlayerStep playerStep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Piece> getSquares() {
        return squares;
    }

    public void setSquares(Map<Integer, Piece> squares) {
        this.squares = squares;
    }

    public Map<Piece, Integer> getScore() {
        return score;
    }

    public void setScore(Map<Piece, Integer> score) {
        this.score = score;
    }

    public Integer getScorePenalty() {
        return scorePenalty;
    }

    public void setScorePenalty(Integer scorePenalty) {
        this.scorePenalty = scorePenalty;
    }

    public Integer getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(Integer playerOrder) {
        this.playerOrder = playerOrder;
    }

    public boolean isMasterBuilder() {
        return masterBuilder;
    }

    public void setMasterBuilder(boolean masterBuilder) {
        this.masterBuilder = masterBuilder;
    }

    public boolean isTurnToPlace() {
        return turnToPlace;
    }

    public void setTurnToPlace(boolean turnToPlace) {
        this.turnToPlace = turnToPlace;
    }

    public PlayerStep getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(PlayerStep playerStep) {
        this.playerStep = playerStep;
    }
}
