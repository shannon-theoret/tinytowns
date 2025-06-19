package com.shannontheoret.tinytowns;

import com.shannontheoret.tinytowns.dto.GameDto;
import com.shannontheoret.tinytowns.dto.PlayerDto;
import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.entity.JPAPlayer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    public GameDto toDto(JPAGame game) {
        List<PlayerDto> playerDtos = game.getPlayers().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        GameDto dto = new GameDto();
        dto.setCode(game.getCode());
        dto.setPlayers(playerDtos);
        dto.setStep(game.getStep());
        dto.setResource(game.getResource());
        dto.setCards(new HashMap<>(game.getCards())); // defensive copy
        return dto;
    }

    public PlayerDto toDto(JPAPlayer player) {
        PlayerDto dto = new PlayerDto();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setSquares(new HashMap<>(player.getSquares()));
        dto.setScore(new HashMap<>(player.getScore()));
        dto.setScorePenalty(player.getScorePenalty());
        dto.setPlayerOrder(player.getPlayerOrder());
        dto.setMasterBuilder(player.getMasterBuilder());
        dto.setPlayerStep(player.getPlayerStep());
        return dto;
    }
}