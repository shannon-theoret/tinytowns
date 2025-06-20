package com.shannontheoret.tinytowns.dao;

import com.shannontheoret.tinytowns.entity.JPAGame;

import java.util.List;

public interface GameDao {
    public List<JPAGame> findAll();

    public JPAGame findByCode(String code);

    public void save(JPAGame game);

    public void flush();

}
