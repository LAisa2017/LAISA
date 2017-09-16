/**
 * 
 */
package www.com.screenplay.awards.fileupload;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 * @author ssara
 *
 */
public interface ScreenPlayFileUpload {
	public void upload() ;
	public Part getFile() ;
	public void setFile(Part file);
	public void validateFile(FacesContext ctx,UIComponent comp,Object value) throws Exception;
	public void save();
}
