package com.shannontheoret.tinytowns.dto;

import com.shannontheoret.tinytowns.BuildingName;
import com.shannontheoret.tinytowns.GameStep;
import com.shannontheoret.tinytowns.Piece;

import java.util.List;
import java.util.Map;

public class GameDto {
    private String code;
    private List<PlayerDto> players;
    private GameStep step;
    private Piece resource;
    private Map<Piece, BuildingName> cards;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public GameStep getStep() {
        return step;
    }

    public void setStep(GameStep step) {
        this.step = step;
    }

    public Piece getResource() {
        return resource;
    }

    public void setResource(Piece resource) {
        this.resource = resource;
    }

    public Map<Piece, BuildingName> getCards() {
        return cards;
    }

    public void setCards(Map<Piece, BuildingName> cards) {
        this.cards = cards;
    }
}
