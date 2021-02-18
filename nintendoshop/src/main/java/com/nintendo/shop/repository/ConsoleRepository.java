package com.nintendo.shop.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nintendo.shop.models.Console;
import com.nintendo.shop.models.Game;

@Transactional
@Repository
public class ConsoleRepository {

	@PersistenceContext
	EntityManager em;
	
	public List<Console> getConsoles() {
		Query q = em.createQuery("select c from Console c");
		return q.getResultList();
	}
	
	public List<Console> getFirstFourConsoles() {
		Query q = em.createQuery("select c from Console c limit 0, 4");
		return q.getResultList();
	}
	
	public List<String> getConsoleTypes() {
		Query q = em.createQuery("select distinct c.type from Console c");
		return q.getResultList();
	}
	
	public Console save(Console console) {
		em.merge(console);
		return console;
	}
	
	public boolean delete(int id) {
		Query q = em.createQuery("delete from Console c where c.id = :id");
		int deleteCount = q.setParameter("id", id).executeUpdate();
		return true;
	}

	public List<Console> getAllGamesContaining(String search) {
		List<Console> consoles = new ArrayList<>();
		
		Query q = em.createQuery("select c from Console c where c.title like '%" + search + "%' ");
		consoles = q.getResultList();
		
		return consoles;
	}
	
}
