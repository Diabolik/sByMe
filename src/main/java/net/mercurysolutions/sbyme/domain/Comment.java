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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.mercurysolutions.sbyme.interfaces.IModel;

@SuppressWarnings("serial")
@Entity
public class Comment extends BaseObject  implements IModel {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String comment;

	@OneToOne
	private User owner;

	@ManyToOne
	private Interest event;
	
	@Override
	public Object toEntity() {
		ObjectMapper mapper = new ObjectMapper();
		CommentEntity entity = mapper.convertValue(this, CommentEntity.class);
		return entity;
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

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the event
	 */
	public Interest getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Interest event) {
		this.event = event;
	}
}
