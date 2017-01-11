/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.controller;

import java.util.List;

import net.mercurysolutions.sbyme.domain.User;
import net.mercurysolutions.sbyme.entity.UserEntity;
import net.mercurysolutions.sbyme.exception.EventsErrorCode;
import net.mercurysolutions.sbyme.services.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "User Model API", value = "")
public class UserController {

	private final static Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@ApiOperation(value = "User login in sByMe", notes = "User login in sByMe")
	public ResponseEntity<UserEntity> loginUser(@RequestBody UserEntity user) {
		String login = user.getEmail();
		String password = user.getPassword();
		
		ResponseEntity<UserEntity> response = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		//Search the user by email
		List<User> users = userService.findAllByEmailAndPassword(login, password);
		
		if(users.isEmpty()) {
			//Search the user by nickname
			login = user.getNickname();
			users = userService.findByNicknameAndPassword(login, password);
			if(users.isEmpty()) {
				EventsErrorCode managedError = EventsErrorCode.USER_NOT_FOUND;
				log.error(managedError);
				responseHeaders.add("ERROR", managedError.toString());
				return new ResponseEntity<UserEntity>(null, responseHeaders, HttpStatus.NOT_FOUND);
			}
		}
		
		UserEntity entity = (UserEntity) users.get(0).toEntity();
		response = new ResponseEntity<UserEntity>(entity, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ApiOperation(value = "Save the user provided", notes = "Save the user provided")
	public ResponseEntity<UserEntity> save(@RequestBody UserEntity entity) {
		ResponseEntity<UserEntity> response = null;
		HttpHeaders responseHeaders = new HttpHeaders();

		emailExists(entity.getEmail(), responseHeaders);
		if (!responseHeaders.isEmpty()) {
			return new ResponseEntity<UserEntity>(null, responseHeaders, HttpStatus.NOT_FOUND);
		}

		nicknameExists(entity.getNickname(), responseHeaders);
		if (!responseHeaders.isEmpty()) {
			return new ResponseEntity<UserEntity>(null, responseHeaders, HttpStatus.NOT_FOUND);
		}

		User user = (User) entity.toModel();
		//Filling default data.
		user.initBasicUser();
		
		//Saving and converting user
		entity = (UserEntity) userService.save(user).toEntity();

		response = new ResponseEntity<UserEntity>(entity, HttpStatus.OK);
		return response;
	}
	
	
	/**
	 * Verify if the email is used
	 * 
	 * @param email
	 * @param headers
	 */
	private void emailExists(String email, HttpHeaders headers) {
		if (userService.findByEmail(email) != null) {
			EventsErrorCode managedError = EventsErrorCode.USER_FOUND_EMAIL;
			log.error(managedError);
			headers.add("ERROR", managedError.toString());
		}
	}

	/**
	 * Verify if the nickname is used
	 * 
	 * @param nickname
	 * @param headers
	 */
	private void nicknameExists(String nickname, HttpHeaders headers) {
		if (userService.findByNickname(nickname) != null) {
			EventsErrorCode managedError = EventsErrorCode.USER_FOUND_NICKNAME;
			log.error(managedError);
			headers.add("ERROR", managedError.toString());
		}
	}

}