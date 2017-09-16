/**
 * 
 */
package www.com.screenplay.awards.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

/**
 * @author sarans
 * 
 */
public class ContextProvider implements ApplicationContextAware,ServletContextAware{
	
	private static ApplicationContext applicationContext;;
	private static ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		ContextProvider.servletContext = servletContext;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ContextProvider.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public static ServletContext getServletContext(){
		return servletContext;
	}
}
