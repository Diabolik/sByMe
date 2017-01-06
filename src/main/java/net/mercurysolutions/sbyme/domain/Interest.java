/*
 * *******************************************************************************
 *   Copyright 2015 Mercury Solutions.
 * *******************************************************************************
 */
package net.mercurysolutions.sbyme.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import net.mercurysolutions.sbyme.entity.InterestEntity;
import net.mercurysolutions.sbyme.interfaces.IModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@Entity
public class Interest extends BaseObject implements IModel {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String place;
		
	@OneToOne
	@JsonIgnore
	private User owner;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "event")
	private Set<Comment> comments;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "event")
	private Set<GalleryItem> gallery;
	
	@Override
	public Object toEntity() {
		ObjectMapper mapper = new ObjectMapper();
		InterestEntity entity = mapper.convertValue(this, InterestEntity.class);
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the comments
	 */
	public Set<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the gallery
	 */
	public Set<GalleryItem> getGallery() {
		return gallery;
	}

	/**
	 * @param gallery the gallery to set
	 */
	public void setGallery(Set<GalleryItem> gallery) {
		this.gallery = gallery;
	}
}
