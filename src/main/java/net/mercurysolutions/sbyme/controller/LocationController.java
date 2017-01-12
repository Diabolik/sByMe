/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.controller;

import net.mercurysolutions.sbyme.domain.Location;
import net.mercurysolutions.sbyme.entity.LocationEntity;
import net.mercurysolutions.sbyme.exception.EventsErrorCode;
import net.mercurysolutions.sbyme.services.LocationService;
import net.mercurysolutions.sbyme.services.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Location Model API", value = "")
public class LocationController {

	private final static Log log = LogFactory.getLog(LocationController.class);

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/locations", method = RequestMethod.POST)
	@ApiOperation(value = "Save the location provided", notes = "Save the location provided")
	public ResponseEntity<LocationEntity> save(@PathVariable("id") Long userId, @RequestBody LocationEntity entity) {
		ResponseEntity<LocationEntity> response = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		EventsErrorCode managedError;
		
		if (!userId.equals(entity.getOwner().getId())) {
			managedError = EventsErrorCode.LOCATION_USERS_NOT_MATCH;
			log.error(managedError);
			responseHeaders.add("ERROR", managedError.toString());
			response = new ResponseEntity<LocationEntity>(HttpStatus.BAD_REQUEST);
			return response;
		}

		if (!userService.exists(userId)) {
			managedError = EventsErrorCode.LOCATION_USERS_NOT_EXISTS;
			log.error(managedError);
			responseHeaders.add("ERROR", managedError.toString());
			response = new ResponseEntity<LocationEntity>(HttpStatus.NOT_FOUND);
			return response;
		}

		Location location = (Location) entity.toModel();

		//Saving and converting the location
		entity = (LocationEntity) locationService.save(location).toEntity();

		response = new ResponseEntity<LocationEntity>(entity, HttpStatus.OK);
		return response;
	}
}