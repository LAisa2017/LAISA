/**
 * 
 */
package www.com.screenplay.awards.service.implementation;

import www.com.screenplay.awards.backingbean.base.BackingBeanBase;
import www.com.screenplay.awards.service.contract.SpAvardsConversationService;
import www.com.screenplay.awards.spring.custom.scope.SpAvardsConversationSpringScope;
import www.com.screenplay.awards.util.ContextProvider;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.support.XmlWebApplicationContext;


/**
 * @author sarans
 *
 */
public class SpAvardsConversationServiceImpl implements SpAvardsConversationService {

	/* (non-Javadoc)
	 * @see www.com.screenplay.awards.service.contract.SpAvardsConversationService#cleanConversation()
	 */
	@Override
	public void cleanConversation() {
		ApplicationContext applicationContext = ContextProvider.getApplicationContext();
		if (applicationContext instanceof XmlWebApplicationContext) {
			XmlWebApplicationContext xmlWebApplicationContext = (XmlWebApplicationContext) applicationContext;
			SpAvardsConversationSpringScope scope = 
					(SpAvardsConversationSpringScope) 
					xmlWebApplicationContext.getBeanFactory().getRegisteredScope(SpAvardsConversationSpringScope.CONVERSATION_ID);
			scope.cleanScope();

			RequestScope requestScope = (RequestScope) xmlWebApplicationContext.getBeanFactory().getRegisteredScope("request");
			
			Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
			for (Object key : m.keySet()) {
				if (m.get(key) instanceof BackingBeanBase) {
					m.remove(key);
				}
			}
		}
	}

}
