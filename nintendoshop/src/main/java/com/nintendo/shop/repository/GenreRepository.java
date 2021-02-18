package com.nintendo.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nintendo.shop.models.Genre;

@Transactional
@Repository
public class GenreRepository {

	@PersistenceContext
	EntityManager em;

	public List<Genre> getGenres() {
		Query q = em.createQuery("select g from Genre g");
		return q.getResultList();
	}
	
	public Genre getGenreById(int id) {
		Query q = em.createQuery("select g from Genre g where g.id = :id");
		q.setParameter("id", id);
		return (Genre) q.getSingleResult();
	}

}
