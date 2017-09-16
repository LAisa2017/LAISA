/**
 * 
 */
package www.com.screenplay.awards.dao.generic;

import java.io.Serializable;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * @author sarans
 *
 */
public interface GenericDAO<T, ID> extends Serializable {
	

	
	T findById(ID id, boolean lock);
	List<T> findAll();
	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	List<T> findByNamedQuery(Map<?, ?> arguments, Integer maxRezSize);
	List<T> findByNamedQuery(Map<?, ?> arguments);
	List<T> findByNamedQuery(Map<?, ?> arguments,String queryName);
	List<T> findByHNamedQuery(Map<?,?> arguments, String queryName);
	T makePersistant(T entity);
	T save(T entity);
	T merge(T entity);
	void makeTranzient(T entity);
	void flush();
	void clear();
	void setQueryName(String queryName);
	void setSession(Session session);
	Session getSession();
	T createInstance();
	T createInstance(Object param);
	
	Long updateByNamedQuery(final String queryName, Map<?,?> parms);
//	Long updateByStoredProcedureCall(String procedureName,Map<?,?> parms);
	ResultSetMetaData getMetaData(String testQuery, Map<?,?> params);
}
