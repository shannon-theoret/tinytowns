package com.shannontheoret.tinytowns.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shannontheoret.tinytowns.Piece;
import com.shannontheoret.tinytowns.PlayerStep;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity (name="player")
public class JPAPlayer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JPAGame game;

    @ElementCollection
    @CollectionTable(name="square", joinColumns = @JoinColumn(name = "player"))
    @MapKeyColumn(name="square_index")
    @Column(name="piece")
    @Enumerated(EnumType.STRING)
    private Map<Integer, Piece> squares = new HashMap<>();

    @ElementCollection
    @CollectionTable(name="score", joinColumns = @JoinColumn(name = "player"))
    @MapKeyColumn(name="piece")
    @Column(name="scoreNumber")
    private Map<Piece, Integer> score = new EnumMap<>(Piece.class);

    @Column(name="scorePenalty")
    private Integer scorePenalty;

    @Column(name="playerOrder", nullable = false)
    private Integer playerOrder;

    @Column(name="masterBuilder")
    private boolean masterBuilder;

    @Column(name="player_step")
    @Enumerated(EnumType.STRING)
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

    public JPAGame getGame() {
        return game;
    }

    public void setGame(JPAGame game) {
        this.game = game;
    }

    public Map<Integer, Piece> getSquares() {
        return squares;
    }

    public void setSquares(SortedMap<Integer, Piece> squares) {
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

    public Boolean getMasterBuilder() {
        return masterBuilder;
    }

    public void setMasterBuilder(Boolean masterBuilder) {
        this.masterBuilder = masterBuilder;
    }

    public PlayerStep getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(PlayerStep playerStep) {
        this.playerStep = playerStep;
    }

    public Map<Integer, Piece> getPortionOfGrid(Set<Integer> indexes) {
        Map<Integer, Piece> portionOfPlayerGrid = squares.entrySet().stream()
                .filter(entry -> indexes.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return portionOfPlayerGrid;
    }
}
