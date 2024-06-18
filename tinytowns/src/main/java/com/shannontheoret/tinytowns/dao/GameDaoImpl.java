package com.shannontheoret.tinytowns.dao;

import com.shannontheoret.tinytowns.entity.JPAGame;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    private EntityManager entityManager;

    @Autowired
    public GameDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<JPAGame> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from game", JPAGame.class);
        List<JPAGame> games = query.getResultList();
        return games;
    }

    @Override
    public JPAGame findByCode(String code) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(JPAGame.class, code);
    }

    @Override
    public void save(JPAGame game) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(game);
    }

    @Override
    public void deleteByCode(String code) {

    }
}
