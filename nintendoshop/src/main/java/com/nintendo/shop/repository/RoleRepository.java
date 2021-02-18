package com.nintendo.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nintendo.shop.models.Role;

@Transactional
@Repository
public class RoleRepository {

	@PersistenceContext
	EntityManager em;

	public Role getRoleById(int id) {
		Query q = em.createQuery("select r from Role r where r.id = :id");
		q.setParameter("id", id);
		return (Role) q.getSingleResult();
	}

}
