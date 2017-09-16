/**
 * 
 */
package www.com.screenplay.awards.backingbean.base;

import javax.faces.context.FacesContext;

/**
 * @author sarans
 *
 */
public abstract class BackingBeanBase implements BackingBean {
	protected boolean isPostback() {
	    return FacesContext.getCurrentInstance().isPostback();
	}
}
