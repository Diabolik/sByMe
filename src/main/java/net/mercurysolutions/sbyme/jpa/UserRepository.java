/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.jpa;

import net.mercurysolutions.sbyme.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public User findByNickname(String nickname);
}
