package com.nintendo.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nintendo.shop.dto.UserDto;
import com.nintendo.shop.models.Game;
import com.nintendo.shop.models.User;

@Transactional
@Repository
public class UserRepository {

	@PersistenceContext
	EntityManager em;

	public User findUserByEmailAndPassword(String email, String password) {
		Query q = em.createQuery("select u from User u where u.email = :email and u.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);

		/*
		 * System.out.println("**********************"); System.out.println("Customer "
		 * + q.getSingleResult()); System.out.println("**********************");
		 */

		User user = null;
		try {
			user = (User) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("No result found");
		}

		return user;
	}

	public User findUserByEmail(String email) {
		Query q = em.createQuery("select u from User u where u.email = :email");
		q.setParameter("email", email);

		User user = null;
		try {
			user = (User) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("No result found");
		}

		return user;
	}

	public User save(User user) {
		em.persist(user);
		return user;
	}

	public User findById(int id) {
		User user = null;
		Query q = em.createQuery("select u from User u where u.id = :id");
		q.setParameter("id", id);

		try {
			user = (User) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("No result found");
		}

		return user;
	}

	public boolean update(User user) {
		User userInfo = this.findById(user.getId());
		user.setPassword(userInfo.getPassword());
		user.setRoles(userInfo.getRoles());
	
		if (user.getImage().equals("")) {
			user.setImage(userInfo.getImage());
		}

		em.merge(user);
		return true;
	}

}
