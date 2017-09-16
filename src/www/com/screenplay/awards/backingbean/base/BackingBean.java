/**
 * 
 */
package www.com.screenplay.awards.backingbean.base;

import javax.faces.event.ComponentSystemEvent;



/**
 * @author sarans
 *
 */
public interface BackingBean {
//	public void onDecode(FacesContext facesContext);
//	public void onPreRender(FacesContext facesContext);
	public void onPreRender(ComponentSystemEvent event);
}
