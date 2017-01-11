/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.entity;

import net.mercurysolutions.sbyme.domain.Address;
import net.mercurysolutions.sbyme.interfaces.IEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressEntity implements IEntity {
	private Long id;
	
	@Override
	public Object toModel() {
		ObjectMapper mapper = new ObjectMapper();
		Address address = mapper.convertValue(this, Address.class);
		return address;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}