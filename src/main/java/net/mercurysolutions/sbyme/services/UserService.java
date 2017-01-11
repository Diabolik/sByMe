/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import net.mercurysolutions.sbyme.domain.User;
import net.mercurysolutions.sbyme.jpa.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	/**
	 * Find User by email
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	/**
	 * Find User by nickname
	 * 
	 * @param nickname
	 * @return
	 */
	public User findByNickname(String nickname) {
		User user = userRepository.findByNickname(nickname);
		return user;
	}
	
	/**
	 * Find User by email and password
	 * 
	 * @param email
	 * @return
	 */
	public List<User> findAllByEmailAndPassword(String email, String password) {
		List<User> users = userRepository.findAllByEmailAndPassword(email, password);
		return users;
	}

	/**
	 * Find User by nickname and password
	 * 
	 * @param nickname
	 * @return
	 */
	public List<User> findByNicknameAndPassword(String nickname, String password) {
		List<User> users = userRepository.findAllByNicknameAndPassword(nickname, password);
		return users;
	}
	
	/**
	 * Returns a detached User. This method is used for update method
	 * 
	 * @param id
	 * @return
	 */
	public User findUserDetached(long id) {
		User user = userRepository.findOne(id);
		entityManager.detach(user);
		return user;
	}

	/**
	 * Saves an user.
	 * 
	 * @param user
	 *            User to save
	 * 
	 * @return Saved user
	 */
	public User save(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
}