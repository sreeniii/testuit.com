package com.testuit.app.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.testuit.app.dao.IGenericDao;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericDaoImpl.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, ID extends Serializable> extends AbstractDaoHibernateImpl implements IGenericDao<T, ID> {

	/** The persistent class. */
    protected Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	/**
	 * Instantiates a new generic dao impl.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
    public GenericDaoImpl (final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#find(java.io.Serializable)
	 */
    public T find(Serializable id) {
        return _get(persistentClass, id);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#find(java.io.Serializable[])
	 */
    public T[] find(Serializable... ids) {
        return _get(persistentClass, ids);
    }

	/**
	 * Save1.
	 * 
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
    public boolean save1(T entity) {
        return _saveOrUpdateIsNew(entity);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#save(java.lang.Object)
	 */
    public boolean save(Object entity) {
        return _saveOrUpdateIsNew(entity);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#saveAsNew(java.lang.Object)
	 */
    public Serializable saveAsNew(Object entity) {
        return _save(entity);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#save(java.lang.Object[])
	 */
    public boolean[] save(Object... entities) {
        return _saveOrUpdateIsNew(entities);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#update(java.lang.Object)
	 */
    public void update(Object entity) {
        _update(entity);
    }

	/**
	 * Refresh.
	 * 
	 * @param entities
	 *            the entities
	 */
    public void refresh(T... entities) {
        _refresh(entities);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#remove(java.lang.Object)
	 */
    public boolean remove(T entity) {
        return _deleteEntity(entity);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#remove(java.lang.Object[])
	 */
    public void remove(T... entities) {
        _deleteEntities(entities);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#removeById(java.io.Serializable)
	 */
    public boolean removeById(Serializable id) {
        return _deleteById(persistentClass, id);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#removeByIds(java.io.Serializable[])
	 */
    public void removeByIds(Serializable... ids) {
        _deleteById(persistentClass, ids);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#exists(java.io.Serializable)
	 */
    public boolean exists(ID id) {
        return _exists(persistentClass, id);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#exists(java.io.Serializable[])
	 */
    public boolean[] exists(ID... ids) {
        return _exists(persistentClass, ids);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#load(java.io.Serializable)
	 */
    public T load(ID id) {
        return _load(persistentClass, id);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#load(java.io.Serializable[])
	 */
    public T[] load(ID... ids) {
        return _load(persistentClass, ids);
    }

	/*
	 * (non-Javadoc)
	 * @see com.advertise.dao.IGenericDao#loadIfExist(java.io.Serializable[])
	 */
    public T[] loadIfExist(ID... ids) {
        Object[] retVal = (Object[]) Array.newInstance(persistentClass, ids.length);
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null)
                retVal[i] = _load(persistentClass, ids[i]);
        }
        return (T[]) retVal;
    }

	/**
	 * Flush.
	 */
    public void flush() {
        _flush();
    }

	/**
	 * Creates the temp table.
	 * 
	 * @return the string
	 */
    public String createTempTable(){
        try {
            Calendar cal = GregorianCalendar.getInstance();
            
            String tempTableName = "temp_" +  cal.getTimeInMillis() + "_" + Thread.currentThread().getId();
            
            dropTempTable(tempTableName);
            
            String createTable = "CREATE TABLE IF NOT EXISTS " + tempTableName + "(id BIGINT NOT NULL, PRIMARY KEY (id))";               
            SQLQuery query = getSession().createSQLQuery(createTable);
            query.executeUpdate();
            return tempTableName;
        } catch (Exception e) {
			throw new RuntimeException(e.getMessage());
        }
    }

	/**
	 * Insert data into temp table.
	 * 
	 * @param tempTableName
	 *            the temp table name
	 * @param ids
	 *            the ids
	 */
    public void insertDataIntoTempTable(String tempTableName, List<Long> ids){
        try {
     	   StringBuffer insertQuery = new StringBuffer();
     	   
     	   insertQuery.append("INSERT INTO " + tempTableName + " (id) VALUES ");
           Calendar cal = GregorianCalendar.getInstance();
            
     	   for (int i = 0; i < ids.size(); i++) {
     		   insertQuery.append("(" + ids.get(i) + ")");
                if(i != (ids.size() - 1)) {
             	   insertQuery.append(",");
                }
            }
     	   SQLQuery query = getSession().createSQLQuery(insertQuery.toString());
     	   System.out.println("Insert Query " + query);
           query.executeUpdate();
            
        } catch (Exception e) {
        	throw new RuntimeException(e.getMessage());
        }
    }
    
	/**
	 * Drop temp table.
	 * 
	 * @param tempTableName
	 *            the temp table name
	 */
    public void dropTempTable(String tempTableName) {
        StringBuffer sqlDropTable = new StringBuffer();
        sqlDropTable.append(" DROP TABLE IF EXISTS " + tempTableName );
        SQLQuery query = getSession().createSQLQuery(sqlDropTable.toString());
        query.executeUpdate();
    }
}
