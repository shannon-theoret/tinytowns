package com.shannontheoret.tinytowns.dto;

public class AddPlayerResponseDto {
    private GameDto game;
    private Long playerId;

    public AddPlayerResponseDto(GameDto game, Long playerId) {
        this.game = game;
        this.playerId = playerId;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto gameDto) {
        this.game = gameDto;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
