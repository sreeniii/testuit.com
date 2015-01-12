package com.testuit.app.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;

// TODO: Auto-generated Javadoc
/**
 * The Class PersistentEntity.
 */
@MappedSuperclass
public class PersistentEntity {

	/** The newid. */
	private static Long NEWID =  new Long(0);

	/** The id. */
	@Id
	@GeneratedValue
	@AccessType("property")
	protected Long id;
	  
	
	/**
	 * Instantiates a new persistent entity.
	 */
	public PersistentEntity() {
		// default constructor
	}
		
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
//	public <T> T id(Long id) {
//		this.id = id;
//		return (T)this;
//	}
	
	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
		
	/**
	 * Checks if is new.
	 * 
	 * @return true, if is new
	 */
	public boolean isNew() {
		return (this.id == null || NEWID.equals(id));
	}
	
	
}
