package com.nintendo.shop.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.nintendo.shop.dto.GameDto;
import com.nintendo.shop.models.Game;

@Transactional
@Repository
public class GameRespository {

	@PersistenceContext
	EntityManager em;

	public List<Game> getGames() {
		List<Game> games = new ArrayList<>();
		Query q = em.createQuery("select g from Game g");
		games = q.getResultList();
		/*
		System.out.println("********************");
		for(Game game: games) {
			System.out.println(game.getId());
		}
		System.out.println(games.size());
		System.out.println("********************");*/
		return games;
	}
	
	public List<Game> getAllGamesContaining(String search){
		List<Game> games = new ArrayList<>();
		
		Query q = em.createQuery("select g from Game g where g.title like '%" + search + "%' ");
		games = q.getResultList();
		
		return games;
	}

	public Game getGameById(int id) {
		Query q = em.createQuery("select g from Game g where g.id = :id");
		q.setParameter("id", id);
		return (Game) q.getSingleResult();
	}

	public Game save(Game game) {
		em.persist(game);
		return game;
	}

	public boolean update(Game game) {
		em.merge(game);
		return true;
	}

	public boolean delete(int id) {
		Query q = em.createQuery("delete from Game g where g.id = :id");
		int deleteCount = q.setParameter("id", id).executeUpdate();
		return true;
	}

	public List<Game> getGamesByPgRatings(List<String> pgRatings) {
		String pgRatingsSearch = "";
		if (pgRatings.size() == 1) {
			pgRatingsSearch = "'" + pgRatings.get(0) + "'";
		} else {
			for (int i = 0; i < pgRatings.size(); i++) {
				if (i == pgRatings.size() - 1) {
					pgRatingsSearch += "'" + pgRatings.get(i) + "'";
					break;
				}
				pgRatingsSearch += "'" + pgRatings.get(i) + "', ";
			}
		}

		Query q = em.createQuery("select g from Game g where g.pgRating in (" + pgRatingsSearch + ")");
		return q.getResultList();
	}

}
