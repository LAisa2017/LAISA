/**
 * 
 */
package www.com.screenplay.awards.dao.generic;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;


import org.hibernate.Criteria;
//import org.hibernate.SQLQuery;
//import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.jdbc.Work;

import www.com.screenplay.awards.util.ScreenPlayAvardsUtil;





/**
 * @author sarans
 *
 */
public abstract class GenericDAOImpl<T, ID> implements Serializable, GenericDAO<T, ID>{
	
//	@SuppressWarnings("unused")
	protected String queryName;
	protected Session session;
	protected String persistentClassName;
	protected Class<T> persistentClass;
	//protected final String ALTER_SCHEMA = "ALTER SESSION SET CURRENT_SCHEMA = ";
	//protected String defaultSchema;
	
//	protected void alterSchema(){
//		StringBuffer sqlBuf = new StringBuffer(ALTER_SCHEMA);
//		sqlBuf.append(this.defaultSchema);
//		SQLQuery query = this.getSession().createSQLQuery(sqlBuf.toString());
//		query.executeUpdate();
//	}
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		ParameterizedType pt = getParameterizedType(getClass());
		if(pt==null)return;
		this.persistentClass = (Class<T>) getParameterizedType(getClass()).getActualTypeArguments()[0];
		this.persistentClassName = this.persistentClass.getName();
	}
	
	
	private ParameterizedType getParameterizedType(Class<?> clss){
		Type type = clss.getGenericSuperclass();
		if(type==null)return null;
		if(type.getClass().getCanonicalName().contains("ParameterizedType")){
			return (ParameterizedType) type;
		}else if(type.getClass().getCanonicalName().contains("Class")){
			return getParameterizedType(clss.getSuperclass());
		}else{
			return null;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4356789978021532371L;

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T createInstance() {
		T ret = null;
		try {
			ret = this.persistentClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			ret = null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			ret = null;
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T createInstance(Object param){
		T ret = null;
		Class<?> paramClass = param.getClass();
		try {
			Constructor<?> constructor = this.persistentClass.getDeclaredConstructor(paramClass);
			ret = ((T) (constructor.newInstance(param)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		//Transaction tx = session.beginTransaction();
		String[] parts = this.persistentClassName.split(ScreenPlayAvardsUtil.DOT);
		StringBuffer sql = new StringBuffer("from ");
		sql.append(parts[parts.length-1]);
		Query query = session.createQuery(sql.toString());
		List<T> ret = query.list();// + persistentClass);
		//tx.commit();
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		Criteria crit = getSession().createCriteria(persistentClass);
		Example example = Example.create(exampleInstance).enableLike();
		if (excludeProperty != null) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		crit.add(example);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id, boolean lock) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		return (T) this.getSession().get(persistentClass, (Serializable) id);
	}
	
	
	@SuppressWarnings("unused")
	private Query createQuery(Map<?, ?> arguments,String queryName){
		Query sqlQuery = this.session.getNamedQuery(queryName);
		//defect 4671 allow arguments Map to be null;
		if(arguments == null)return sqlQuery;
		Iterator<?> iterator = arguments.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			sqlQuery.setParameter(key, arguments.get(key));
		}
		return sqlQuery;
	}
	
	protected Query setParameters(Map<?, ?> arguments,Query sqlQuery){
		//Query sqlQuery = this.session.getNamedQuery(queryName);
		//defect 4671 allow arguments Map to be null;
		if(arguments == null)return sqlQuery;
		Iterator<?> iterator = arguments.keySet().iterator();
		while(iterator.hasNext()){
			Object keyObj = iterator.next();
			if(keyObj instanceof Integer){
				int key = ((Integer)keyObj).intValue();
				sqlQuery.setParameter(key, arguments.get(key));
			}else if(keyObj instanceof String){
				String key = (String) keyObj;
				sqlQuery.setParameter(key, arguments.get(key));
			}
			
		}
		return sqlQuery;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(Map<?,?> arguments, Integer maxRezSize) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		String queryStr = this.getSession().getNamedQuery(this.persistentClassName).getQueryString();
		Query query = this.getSession().createSQLQuery(queryStr).addEntity(this.persistentClass);
		this.setParameters(arguments, query);
		query.setFetchSize(maxRezSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(Map<?,?> arguments) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		String queryStr = this.getSession().getNamedQuery(this.persistentClassName).getQueryString();
		Query query = this.getSession().createSQLQuery(queryStr).addEntity(this.persistentClass);
		this.setParameters(arguments, query);
		List<T> ret = query.list();
		return ret;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(Map<?,?> arguments, String queryName) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		String queryStr = this.getSession().getNamedQuery(queryName).getQueryString();
		Query query = this.getSession().createSQLQuery(queryStr).addEntity(this.persistentClass);
		this.setParameters(arguments, query);
		List<T> ret = query.list();
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByHNamedQuery(Map<?,?> arguments, String queryName) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		String queryStr = this.getSession().getNamedQuery(queryName).getQueryString();
		Query query = this.getSession().createQuery(queryStr);
		this.setParameters(arguments, query);
		List<T> ret = query.list();
		return ret;
	}


	@Override
	public void flush() {
		this.session.flush();
	}



	@Override
	public Session getSession() {
		return this.session;
	}

	

	@Override
	public T makePersistant(T entity) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		this.session.persist(entity);
		return entity;
	}

	@Override
	public void makeTranzient(T entity) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		this.session.evict(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T entity) {
		T ret = (T)this.session.save(entity);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
		T ret = (T)this.session.merge(entity);
		return ret;
	}


	@Override
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public Long updateByNamedQuery(final String queryName, Map<?,?> parms){
//		if(this.defaultSchema != null){
//			alterSchema();
//		}
//		System.out.println(new Date().toString()+" - Mistrn Started executing query = " +queryName);
		Date startTime = new Date(); 
		
		
		Query sqlQuery = session.getNamedQuery(queryName);
		if(parms != null){
			this.setParameters(parms, sqlQuery);
		}
		Long count = new Long(sqlQuery.executeUpdate());
		
		
		Date endTime = new Date();
		
		System.out.println(new Date().toString()+" - Query  = " +queryName+ " -- executed in "+(endTime.getTime()-startTime.getTime())/1000.0+" seconds.");
		
		return count;
	}
//	public void setDefaultSchema(String defaultSchema) {
//		this.defaultSchema = defaultSchema;
//	}
//	public String getDefaultSchema() {
//		return defaultSchema;
//	}
	
	@SuppressWarnings({ "unused", "unchecked","rawtypes" })
	public ResultSetMetaData getMetaData(final String testQuery,  final Map params){
		final ResultSetMetaData metaData;
		
		WclcGetMetaDataWork gmw = new WclcGetMetaDataWork(testQuery,params);
		this.session.doWork(gmw);
		return gmw.getMetaData();
		
	}
	
	private class WclcGetMetaDataWork implements Work{
		
		public WclcGetMetaDataWork(String testSql,Map<Integer,Object> params){
			this.testQuery = testSql;
			this.params = params;
		}
		private ResultSetMetaData metaData;
		/**
		 * @return the metaData
		 */
		public ResultSetMetaData getMetaData() {
			return metaData;
		}
		
		private String testQuery;
		private Map<Integer,Object> params;
		@Override
		public void execute(Connection con) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement pst = con.prepareStatement(testQuery);
			for(Integer index:this.params.keySet()){
				Object x = this.params.get(index);
				pst.setObject(index, x);
			}
			pst.setMaxRows(1);
	    	ResultSet rs = pst.executeQuery();
	    	metaData = rs.getMetaData();
		}
		
	}
	
}