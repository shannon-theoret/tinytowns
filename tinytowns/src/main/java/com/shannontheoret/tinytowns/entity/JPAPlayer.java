package com.shannontheoret.tinytowns.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shannontheoret.tinytowns.Piece;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
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
    private Map<Integer, Piece> squares;

    @Column(name="playerOrder", nullable = false)
    private Integer playerOrder;

    @Column(name="masterBuilder")
    private boolean masterBuilder;

    @Column(name="turnToPlace")
    private boolean turnToPlace;

    @Column(name="completedGrid")
    private boolean completedGrid;

    @Column(name="turnToBuild")
    private boolean turnToBuild;

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

    public Boolean getTurnToPlace() {
        return turnToPlace;
    }

    public void setTurnToPlace(Boolean turnToPlace) {
        this.turnToPlace = turnToPlace;
    }

    public boolean isCompletedGrid() {
        return completedGrid;
    }

    public void setCompletedGrid(boolean completedGrid) {
        this.completedGrid = completedGrid;
    }

    public boolean getTurnToBuild() {
        return turnToBuild;
    }

    public void setTurnToBuild(boolean turnToBuild) {
        this.turnToBuild = turnToBuild;
    }

    public Map<Integer, Piece> getPortionOfGrid(Set<Integer> indexes) {
        Map<Integer, Piece> portionOfPlayerGrid = squares.entrySet().stream()
                .filter(entry -> indexes.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return portionOfPlayerGrid;
    }
}
