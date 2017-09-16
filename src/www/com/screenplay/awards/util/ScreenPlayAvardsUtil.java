/**
 * 
 */
package www.com.screenplay.awards.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * @author ssaran
 *
 */
public class ScreenPlayAvardsUtil {
	
	public static final String 		DOT = "\\."; 
	
	public static Collection<Part> getAllParts(Part part) throws ServletException, IOException {
		
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    return request.getParts().stream().filter(p -> part.getName().equals(p.getName())).collect(Collectors.toList());
	    
	}
	
	public static byte[] getBytesFromInputStream(InputStream is) throws IOException
	{
	    try (ByteArrayOutputStream os = new ByteArrayOutputStream();)
	    {
	        byte[] buffer = new byte[0xFFFF];

	        for (int len; (len = is.read(buffer)) != -1;)
	            os.write(buffer, 0, len);

	        os.flush();

	        return os.toByteArray();
	    }
	}
}
