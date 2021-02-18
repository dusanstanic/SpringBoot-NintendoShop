package com.nintendo.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nintendo.shop.models.Image;


@Transactional
@Repository
public class ImageRepository {

	@PersistenceContext
	EntityManager em;
	
	public List<Image> getImagesByGameId(int gameId) {
		Query q = em.createQuery("select im from Image im where im.game.id = :gameId");
		q.setParameter("gameId", gameId);
		return q.getResultList();
	}
	
}
