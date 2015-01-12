package com.testuit.app.dao;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGenericDao.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public interface IGenericDao<T, ID extends Serializable> {

	/**
	 * <p>
	 * Get the entity with the specified type and id from the datastore.
	 * 
	 * <p>
	 * If none is found, return null.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */
	T find(ID id);

	/**
	 * Get all entities of the specified type from the datastore that have one
	 * of these ids.
	 * 
	 * @param ids
	 *            the ids
	 * @return the t[]
	 */
	T[] find(ID... ids);

	/**
	 * <p>
	 * If the id of the entity is null or zero, add it to the datastore and
	 * assign it an id; otherwise, update the corresponding entity in the
	 * datastore with the properties of this entity. In either case the entity
	 * passed to this method will be attached to the session.
	 * 
	 * <p>
	 * If an entity to update is already attached to the session, this method
	 * will have no effect. If an entity to update has the same id as another
	 * instance already attached to the session, an error will be thrown.
	 * 
	 * @param entity
	 *            the entity
	 * @return <code>true</code> if create; <code>false</code> if update.
	 */
	boolean save(Object entity);

	/**
	 * <p>
	 * For each entity, if the id of the entity is null or zero, add it to the
	 * datastore and assign it an id; otherwise, update the corresponding entity
	 * in the datastore with the properties of this entity. In either case the
	 * entity passed to this method will be attached to the session.
	 * 
	 * <p>
	 * If an entity to update is already attached to the session, this method
	 * will have no effect. If an entity to update has the same id as another
	 * instance already attached to the session, an error will be thrown.
	 * 
	 * @param entities
	 *            the entities
	 * @return the boolean[]
	 */
	boolean[] save(Object... entities);

	/**
	 * <p>
	 * Persist the given transient instance and add it to the datastore, first
	 * assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) This operation
	 * cascades to associated instances if the association is mapped with
	 * cascade="save-update".
	 * 
	 * @param entity
	 *            the entity
	 * @return The id of the newly saved entity.
	 */
	public Serializable saveAsNew(Object entity);

	/**
	 * Update.
	 * 
	 * @param entity
	 *            the entity
	 */
	void update(Object entity);

	/**
	 * Remove the specified entity from the datastore.
	 * 
	 * @param entity
	 *            the entity
	 * @return <code>true</code> if the entity is found in the datastore and
	 *         removed, <code>false</code> if it is not found.
	 */
	public boolean remove(T entity);

	/**
	 * Remove all of the specified entities from the datastore.
	 * 
	 * @param entities
	 *            the entities
	 */
	public void remove(T... entities);

	/**
	 * Remove the entity with the specified type and id from the datastore.
	 * 
	 * @param id
	 *            the id
	 * @return <code>true</code> if the entity is found in the datastore and
	 *         removed, <code>false</code> if it is not found.
	 */
	public boolean removeById(ID id);

	/**
	 * Remove all the entities of the given type from the datastore that have
	 * one of these ids.
	 * 
	 * @param ids
	 *            the ids
	 */
	public void removeByIds(ID... ids);

	/**
	 * Exists.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	boolean exists(ID id);

	/**
	 * Exists.
	 * 
	 * @param id
	 *            the id
	 * @return the boolean[]
	 */
	boolean[] exists(ID... id);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */
	T load(ID id);

	/**
	 * Load.
	 * 
	 * @param ids
	 *            the ids
	 * @return the t[]
	 */
	T[] load(ID... ids);

	/**
	 * Load if exist.
	 * 
	 * @param ids
	 *            the ids
	 * @return the t[]
	 */
	T[] loadIfExist(ID... ids);
}
