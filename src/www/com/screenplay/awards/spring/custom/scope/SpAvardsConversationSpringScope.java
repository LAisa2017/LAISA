/**
 * 
 */
package www.com.screenplay.awards.spring.custom.scope;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author sarans
 *     www.com.screenplay.awards.spring.custom.scope.SpAvardsConversationSpringScope
 */
public class SpAvardsConversationSpringScope implements Scope {
	
	public static final String CONVERSATION_ID = "Conversation";
	public Map<String, Object> objectStore = null;
	
	public Map<String, Object> getObjectStore() {
		return objectStore;
	}

	public void setObjectStore(Map<String, Object> objectStore) {
		this.objectStore = objectStore;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#get(java.lang.String, org.springframework.beans.factory.ObjectFactory)
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		
		Object bean = this.getObjectStore().get(name);
		if (bean == null) {
			bean = objectFactory.getObject();
			this.getObjectStore().put(name, bean);
		} else {
			System.err.println("\tLASpA Conversation Factory: Found bean " + name);
		}
		return bean;
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#getConversationId()
	 */
	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return CONVERSATION_ID;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#registerDestructionCallback(java.lang.String, java.lang.Runnable)
	 */
	@Override
	public void registerDestructionCallback(String arg0, Runnable arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#remove(java.lang.String)
	 */
	@Override
	public synchronized Object remove(String name) {
		return this.getObjectStore() == null ? null : this.getObjectStore().remove(name);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#resolveContextualObject(java.lang.String)
	 */
	@Override
	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public synchronized void cleanScope() {
		this.getObjectStore().clear();
	}

}
