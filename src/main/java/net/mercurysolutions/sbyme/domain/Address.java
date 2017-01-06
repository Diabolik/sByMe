/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.mercurysolutions.sbyme.interfaces.IModel;

@SuppressWarnings("serial")
@Entity
public class Address extends BaseObject implements IModel {
	@Id
	@GeneratedValue
	private Long id;
	
	@Override
	public Object toEntity() {
		ObjectMapper mapper = new ObjectMapper();
		AddressEntity entity = mapper.convertValue(this, AddressEntity.class);
		return entity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}