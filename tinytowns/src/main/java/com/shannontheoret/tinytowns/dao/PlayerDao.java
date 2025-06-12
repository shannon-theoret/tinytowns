package com.shannontheoret.tinytowns.dao;

import com.shannontheoret.tinytowns.entity.JPAGame;
import com.shannontheoret.tinytowns.entity.JPAPlayer;

import java.util.List;

public interface PlayerDao {

    List<JPAPlayer> findPlayerByGameCode(String gameCode);

    JPAPlayer findById(Long id);

    void save(JPAPlayer player);
}
