package com.testuit.app.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractDaoHibernateImpl.
 */
@Transactional("transactionManager")
@SuppressWarnings("unchecked")
public class AbstractDaoHibernateImpl {

	/** The session factory. */
    protected final SessionFactory sessionFactory;

	/**
	 * Instantiates a new abstract dao hibernate impl.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
    public AbstractDaoHibernateImpl(final SessionFactory sessionFactory) {
        if (sessionFactory == null)
            throw new IllegalArgumentException("sessionFactory cannot be null");
        this.sessionFactory = sessionFactory;
    }

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	/**
	 * Cast result list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param results
	 *            the results
	 * @return the list
	 */
    protected <T> List<T> castResultList(List<?> results) {
        return (List<T>) results;
    }

	/**
	 * _find by criteria.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
    protected <T> List<T> _findByCriteria(Criteria criteria) {
        return criteria.list();
    }

	/**
	 * _find by query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param query
	 *            the query
	 * @return the list
	 */
    protected <T> List<T> _findByQuery(Query query) {
        return query.list();
    }

	/**
	 * _find by criteria arr.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param type
	 *            the type
	 * @param criteria
	 *            the criteria
	 * @return the t[]
	 */
    protected <T> T[] _findByCriteriaArr(final Class<T> type, Criteria criteria) {
        List l = criteria.list();
        T[] retVal = (T[]) Array.newInstance(type, l.size());
        return (T[]) l.toArray(retVal);
    }

	/**
	 * Find by id.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entityClass
	 *            the entity class
	 * @param id
	 *            the id
	 * @return the t
	 */
    protected <T> T findById(final Class<T> entityClass, final Serializable id) {
        if (null == entityClass)
            throw new IllegalArgumentException("entityClass can't be null");
        if (null == id)
            throw new IllegalArgumentException("id can't be null");
        return (T) getSession().get(entityClass, id);
    }

	/**
	 * Save or update.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entityClass
	 *            the entity class
	 * @param entity
	 *            the entity
	 * @return the t
	 */
    protected <T> T saveOrUpdate(final Class<T> entityClass, final T entity) {
        if (null == entityClass)
            throw new IllegalArgumentException("entityClass can't be null");
        if (null == entity)
            throw new IllegalArgumentException("entity can't be null");
        getSession().saveOrUpdate(entity);
        return entity;
    }

	/**
	 * Delete.
	 * 
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
    public boolean delete(final Object entity) {
        if (null == entity)
            throw new IllegalArgumentException("entity can't be null");
        getSession().delete(entity);
        return true;
    }

	/**
	 * Delete by id.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entityClass
	 *            the entity class
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
    public <T> boolean deleteById(final Class<T> entityClass, final Serializable id) {
        if (null == entityClass)
            throw new IllegalArgumentException("entityClass can't be null");
        if (null == id)
            throw new IllegalArgumentException("id can't be null");
        return delete(findById(entityClass, id));
    }

    /**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance.
	 * <code>get()</code> always hits the database immediately.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @return the t
	 */
    protected <T> T _get(Class<T> type, Serializable id) {
        return (T) getSession().get(type, id);
    }

    /**
	 * <p>
	 * Return the all the persistent instances of the given entity class with
	 * the given identifiers. An array of entities is returned that matches the
	 * same order of the ids listed in the call. For each entity that is not
	 * found in the datastore, a null will be inserted in its place in the
	 * return array.
	 * 
	 * <p>
	 * <code>get()</code> always hits the database immediately.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param type
	 *            the type
	 * @param ids
	 *            the ids
	 * @return the t[]
	 */
    protected <T> T[] _get(Class<T> type, Serializable... ids) {
        Criteria c = getSession().createCriteria(type);
        c.add(Restrictions.in("id", ids));
        Object[] retVal = (Object[]) Array.newInstance(type, ids.length);

        for (Object entity : c.list()) {
            Serializable id = getId(entity);
            for (int i = 0; i < ids.length; i++) {
                if (id.equals(ids[i])) {
                    retVal[i] = entity;
                    break;
                }
            }
        }

        return (T[]) retVal;
    }

    /**
	 * Remove the entity of the specified class with the specified id from the
	 * datastore.
	 * 
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @return <code>true</code> if the object is found in the datastore and
	 *         deleted, <code>false</code> if the item is not found.
	 */
    protected boolean _deleteById(Class<?> type, Serializable id) {
        if (id != null) {
            Object entity = getSession().get(type, id);
            if (entity != null) {
                getSession().delete(entity);
                return true;
            }
        }
        return false;
    }

    /**
	 * Remove all the entities of the given type from the datastore that have
	 * one of these ids.
	 * 
	 * @param type
	 *            the type
	 * @param ids
	 *            the ids
	 */
    protected void _deleteById(Class<?> type, Serializable... ids) {
        Criteria c = getSession().createCriteria(type).add(Restrictions.in("id", ids));
        for (Object entity : c.list()) {
            getSession().delete(entity);
        }
    }

    /**
	 * <p>
	 * Return the persistent instance of the given entity class with the given
	 * identifier, assuming that the instance exists. Throw an unrecoverable
	 * exception if there is no matching database row.
	 * 
	 * <p>
	 * If the class is mapped with a proxy, <code>load()</code> just returns an
	 * uninitialized proxy and does not actually hit the database until you
	 * invoke a method of the proxy. This behaviour is very useful if you wish
	 * to create an association to an object without actually loading it from
	 * the database. It also allows multiple instances to be loaded as a batch
	 * if batch-size is defined for the class mapping.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @return the t
	 */
    protected <T> T _load(Class<T> type, Serializable id) {
        return (T) getSession().load(type, id);
    }

    /**
	 * <p>
	 * Return the persistent instance of the given entity class with the given
	 * identifier, assuming that the instance exists. Throw an unrecoverable
	 * exception if there is no matching database row. An array of entities is
	 * returned that matches the same order of the ids listed in the call. For
	 * each entity that is not found in the datastore, a null will be inserted
	 * in its place in the return array.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param type
	 *            the type
	 * @param ids
	 *            the ids
	 * @return the t[]
	 * @see #_load(Class, Serializable)
	 */
    protected <T> T[] _load(Class<T> type, Serializable... ids) {
        Object[] retVal = (Object[]) Array.newInstance(type, ids.length);
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null)
                retVal[i] = _load(type, ids[i]);
        }
        return (T[]) retVal;
    }

    /**
	 * <p>
	 * Update the persistent instance with the identifier of the given detached
	 * instance. If there is a persistent instance with the same identifier, an
	 * exception is thrown. This operation cascades to associated instances if
	 * the association is mapped with cascade="save-update".
	 * 
	 * <p>
	 * The difference between <code>update()</code> and <code>merge()</code> is
	 * significant: <code>update()</code> will make the given object persistent
	 * and throw and error if another object with the same ID is already
	 * persistent in the Session. <code>merge()</code> doesn't care if another
	 * object is already persistent, but it also doesn't make the given object
	 * persistent; it just copies over the values to the datastore.
	 * 
	 * @param transientEntities
	 *            the transient entities
	 */
    protected void _update(Object... transientEntities) {
        for (Object entity : transientEntities) {
            getSession().update(entity);
        }
    }

	/**
	 * _update.
	 * 
	 * @param entity
	 *            the entity
	 */
    protected void _update(Object entity) {
        getSession().update(entity);
    }

    /**
	 * <p>
	 * Copy the state of the given object onto the persistent object with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * the given instance is unsaved, save a copy of and return it as a newly
	 * persistent instance. The given instance does not become associated with
	 * the session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 * 
	 * <p>
	 * The difference between <code>update()</code> and <code>merge()</code> is
	 * significant: <code>update()</code> will make the given object persistent
	 * and throw and error if another object with the same ID is already
	 * persistent in the Session. <code>merge()</code> doesn't care if another
	 * object is already persistent, but it also doesn't make the given object
	 * persistent; it just copies over the values to the datastore.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entity
	 *            the entity
	 * @return the t
	 */
    protected <T> T _merge(T entity) {
        return (T) getSession().merge(entity);
    }

    /**
	 * <p>
	 * Persist the given transient instance and add it to the datastore, first
	 * assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) This operation
	 * cascades to associated instances if the association is mapped with
	 * cascade="save-update".
	 * 
	 * <p>
	 * This is different from <code>persist()</code> in that it does guarantee
	 * that the object will be assigned an identifier immediately. With
	 * <code>save()</code> a call is made to the datastore immediately if the id
	 * is generated by the datastore so that the id can be determined. With
	 * <code>persist</code> this call may not occur until flush time.
	 * 
	 * @param entity
	 *            the entity
	 * @return The id of the newly saved entity.
	 */
    protected Serializable _save(Object entity) {
        return getSession().save(entity);
    }

    /**
	 * Persist the given transient instances and add them to the datastore,
	 * first assigning a generated identifier. (Or using the current value of
	 * the identifier property if the assigned generator is used.) This
	 * operation cascades to associated instances if the association is mapped
	 * with cascade="save-update".
	 * 
	 * @param entities
	 *            the entities
	 */
    protected void _save(Object... entities) {
        for (Object entity : entities) {
            _save(entity);
        }
    }

    /**
	 * <p>
	 * Calls Hibernate's <code>saveOrUpdate()</code>, which behaves as follows:
	 * 
	 * <p>
	 * Either <code>save()</code> or <code>update()</code> based on the
	 * following rules
	 * <ul>
	 * <li>if the object is already persistent in this session, do nothing
	 * <li>if another object associated with the session has the same
	 * identifier, throw an exception
	 * <li>if the object has no identifier property, save() it
	 * <li>if the object's identifier has the value assigned to a newly
	 * instantiated object, save() it
	 * <li>if the object is versioned (by a &lt;version&gt; or
	 * &lt;timestamp&gt;), and the version property value is the same value
	 * assigned to a newly instantiated object, save() it
	 * <li>otherwise update() the object
	 * </ul>.
	 * 
	 * @param entity
	 *            the entity
	 */
    protected void _saveOrUpdate(Object entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
	 * <p>
	 * If an entity already exists in the datastore with the same id, call
	 * _update and return false (not new). If no such entity exists in the
	 * datastore, call _save() and return true (new)
	 * 
	 * @param entity
	 *            the entity
	 * @return <code>true</code> if _save(); <code>false</code> if _update().
	 */
    protected boolean _saveOrUpdateIsNew(Object entity) {
        if (entity == null)
            throw new IllegalArgumentException("attempt to saveOrUpdate with null entity");

        Serializable id = getId(entity);
        if (getSession().contains(entity))
            return false;

        if (id == null || (new Long(0)).equals(id) || !_exists(entity)) {
            _save(entity);
            return true;
        } else {
            _update(entity);
            return false;
        }
    }

    /**
	 * Either <code>save()</code> or <code>update()</code> each entity,
	 * depending on whether or not an entity with the same id already exists in
	 * the datastore.
	 * 
	 * @param entities
	 *            the entities
	 * @return an boolean array corresponding to to the input list of entities.
	 *         Each element is <code>true</code> if the corresponding entity was
	 *         <code>_save()</code>d or <code>false</code> if it was
	 *         <code>_update()</code>d.
	 */
    protected boolean[] _saveOrUpdateIsNew(Object... entities) {
        Boolean[] exists = new Boolean[entities.length];

        // if an entity is contained in the session, it exists; if it has no id,
        // it does not exist
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] == null) {
                throw new IllegalArgumentException("attempt to saveOrUpdate with null entity");
            }
            if (getSession().contains(entities[i])) {
                exists[i] = true;
            } else {
                Serializable id = getId(entities[i]);
                if (id == null || (new Long(0)).equals(id)) {
                    exists[i] = false;
                }
            }
        }

        // if it has an id and is not contained in the session, it may exist
        Map<Class<?>, List<Integer>> mayExist = new HashMap<Class<?>, List<Integer>>();
        for (int i = 0; i < entities.length; i++) {
            if (exists[i] == null) {
                List<Integer> l = mayExist.get(entities[i].getClass());
                if (l == null) {
                    l = new ArrayList<Integer>();
                    mayExist.put(entities[i].getClass(), l);
                }
                l.add(i);
            }
        }

        // for each type of entity, do a batch call to the datastore to see
        // which of the entities of that class exist
        for (Map.Entry<Class<?>, List<Integer>> entry : mayExist.entrySet()) {
            Serializable[] ids = new Serializable[entry.getValue().size()];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = getId(entities[entry.getValue().get(i)]);
            }
            boolean exists2[] = _exists(entry.getKey(), ids);
            for (int i = 0; i < ids.length; i++) {
                exists[entry.getValue().get(i)] = exists2[i];
            }
        }

        boolean[] isNew = new boolean[entities.length];
        // now that we know which ones exist, save or update each.
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                if (exists[i]) {
                    _update(entities[i]);
                    isNew[i] = false;
                } else {
                    _save(entities[i]);
                    isNew[i] = true;
                }
            }
        }

        return isNew;
    }

    /**
	 * Returns true if the object is connected to the current hibernate session.
	 * 
	 * @param o
	 *            the o
	 * @return true, if successful
	 */
    protected boolean _sessionContains(Object o) {
        return getSession().contains(o);
    }

    /**
     * Flushes changes in the hibernate cache to the datastore.
     */
    protected void _flush() {
        getSession().flush();
    }

    /**
	 * Refresh the content of the given entity from the current datastore state.
	 * 
	 * @param entities
	 *            the entities
	 */
    protected void _refresh(Object... entities) {
        for (Object entity : entities)
            getSession().refresh(entity);
    }

	/**
	 * _exists.
	 * 
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
    protected boolean _exists(Object entity) {
        if (getSession().contains(entity))
            return true;
        return _exists(entity.getClass(), getId(entity));
    }

	/**
	 * _exists.
	 * 
	 * @param type
	 *            the type
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
    protected boolean _exists(Class<?> type, Serializable id) {
        if (type == null)
            throw new NullPointerException("Type is null.");
        if (id == null)
            return false;
        Query query = getSession().createQuery("select id from " + type.getName() + " where id = :id");
        query.setParameter("id", id);
        return query.list().size() == 1;
    }

	/**
	 * _exists.
	 * 
	 * @param type
	 *            the type
	 * @param ids
	 *            the ids
	 * @return the boolean[]
	 */
    protected boolean[] _exists(Class<?> type, Serializable... ids) {
        if (type == null)
            throw new NullPointerException("Type is null.");
        boolean[] ret = new boolean[ids.length];
        // we can't use "id in (:ids)" because some databases do not support
        // this for compound ids.
        StringBuilder sb = new StringBuilder("select id from " + type.getName() + " where");
        boolean first = true;
        for (int i = 0; i < ids.length; i++) {
            if (first) {
                first = false;
                sb.append(" id = :id");
            } else {
                sb.append(" or id = :id");
            }
            sb.append(i);
        }

        Query query = getSession().createQuery(sb.toString());
        for (int i = 0; i < ids.length; i++) {
            query.setParameter("id" + i, ids[i]);
        }

        for (Serializable id : (List<Serializable>) query.list()) {
            for (int i = 0; i < ids.length; i++) {
                if (id.equals(ids[i])) {
                    ret[i] = true;
                    // don't break. the same id could be in the list twice.
                }
            }
        }

        return ret;
    }

    /**
	 * Remove the specified entity from the datastore.
	 * 
	 * @param entity
	 *            the entity
	 * @return <code>true</code> if the object is found in the datastore and
	 *         removed, <code>false</code> if the item is not found.
	 */
    protected boolean _deleteEntity(Object entity) {
        if (entity != null) {
            Serializable id = getId(entity);
            if (id != null) {
                entity = getSession().get(entity.getClass(), id);
                if (entity != null) {
                    getSession().delete(entity);
                    return true;
                }
            }
        }
        return false;
    }

    /**
	 * Remove the specified entities from the datastore.
	 * 
	 * @param entities
	 *            the entities
	 */
    protected void _deleteEntities(Object... entities) {
        for (Object entity : entities) {
            if (entity != null)
                getSession().delete(entity);
        }
    }

	/**
	 * Gets the id.
	 * 
	 * @param object
	 *            the object
	 * @return the id
	 */
    protected Serializable getId(Object object) {
        if (object == null)
            throw new NullPointerException("Cannot get ID from null object.");
        
		return sessionFactory.getClassMetadata(object.getClass()).getIdentifier(object);
    }

}
