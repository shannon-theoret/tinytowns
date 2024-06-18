package com.shannontheoret.tinytowns.entity;

import com.shannontheoret.tinytowns.Building;
import com.shannontheoret.tinytowns.BuildingName;
import com.shannontheoret.tinytowns.GameStep;
import com.shannontheoret.tinytowns.Piece;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity (name="game")
public class JPAGame {

    @Id
    @Column
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    @OrderBy("player_order")
    private List<JPAPlayer> players;


    @Column
    @Enumerated(EnumType.STRING)
    private GameStep step;

    @Column
    @Enumerated(EnumType.STRING)
    private Piece resource;

    @ElementCollection
    @CollectionTable(name="card", joinColumns = @JoinColumn(name = "game"))
    @MapKeyColumn(name="piece")
    @Column(name="building")
    @Enumerated(EnumType.STRING)
    private Map<Piece, BuildingName> cards;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<JPAPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<JPAPlayer> players) {
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
