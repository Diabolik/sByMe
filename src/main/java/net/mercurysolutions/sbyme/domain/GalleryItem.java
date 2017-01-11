package net.mercurysolutions.sbyme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import net.mercurysolutions.sbyme.entity.GalleryItemEntity;
import net.mercurysolutions.sbyme.interfaces.IModel;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@Entity
public class GalleryItem extends BaseObject implements IModel{
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String url;

	@OneToOne
	private User owner;

	@ManyToOne
	private Location location;

	@Override
	public Object toEntity() {
		ObjectMapper mapper = new ObjectMapper();
		GalleryItemEntity entity = mapper.convertValue(this, GalleryItemEntity.class);
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
