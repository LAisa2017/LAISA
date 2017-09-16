/**
 * 
 */
package www.com.screenplay.awards.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import www.com.screenplay.awards.util.ScreenPlayAvardsUtil; 

/**
 * @author ssara
 *
 */
public class ScreenPlayFileUploadImpl implements ScreenPlayFileUpload {
	
	  private Part file;
	  private String fileContent;
	  private String successMessage;
	  private List<FacesMessage> msgs = new ArrayList<FacesMessage>(0);
	  private Collection<Part> files; 
	  private Map<String,byte[]> filesBytes;
	  
    public Map<String, byte[]> getFilesBytes() {
		return filesBytes;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	private final Long maxFileSize = new Long(25000*1024);
	  private final List<String> allowedContentTypes = new ArrayList<String>();
	  {
		  allowedContentTypes.add("application/pdf");
		  allowedContentTypes.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		  allowedContentTypes.add("application/msword");
		  allowedContentTypes.add("text/plain");
	  }
	 
	  @Override
	  public void upload() {
	    try {
	      
	    	this.filesBytes = new HashMap<String,byte[]>(0);
	    	for(Part file:this.files){
	    		String fileName = file.getSubmittedFileName();
	    		InputStream file_is = file.getInputStream();
	    		byte[] file_bytes = ScreenPlayAvardsUtil.getBytesFromInputStream(file_is);
	    		if(this.filesBytes == null){
	    			this.filesBytes = new HashMap<String,byte[]>(0);
	    		}
	    		this.filesBytes.put(fileName, file_bytes);
	    	}
	      this.setSuccessMessage("Congratulations you have successfully applied for competition.");
	    
	    } catch (IOException e) {
	    	List<String> msg = new ArrayList<String>(0);
	    	msg.add(e.getMessage());
	    	this.addFatalMessages(msg);
	    }
	  }
	 
	  public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	@Override
	  public Part getFile() {
	    return file;
	  }
	 
	  @Override
	  public void setFile(Part file) {
	    this.file = file;
	  }
	  
	  private void addErrorMessages(List<String> messages){
		  for(String message : messages){
			  FacesMessage m1 = new FacesMessage();
			  m1.setSeverity(FacesMessage.SEVERITY_ERROR);
			  //m1.setDetail(message);
			  m1.setSummary(message);
			  msgs.add(m1);
		  }
	  }
	  
	  private void addInfoMessages(List<String> messages){
		  for(String message : messages){
			  FacesMessage m1 = new FacesMessage();
			  m1.setSeverity(FacesMessage.SEVERITY_INFO);
			//m1.setDetail(message);
			  m1.setSummary(message);
			  msgs.add(m1);
		  }
	  }
	  
	  private void addWarningMessages(List<String> messages){
		  for(String message : messages){
			  FacesMessage m1 = new FacesMessage();
			  m1.setSeverity(FacesMessage.SEVERITY_WARN);
			//m1.setDetail(message);
			  m1.setSummary(message);
			  msgs.add(m1);
		  }
	  }
	  
	  private void addFatalMessages(List<String> messages){
		  for(String message : messages){
			  FacesMessage m1 = new FacesMessage();
			  m1.setSeverity(FacesMessage.SEVERITY_FATAL);
			//m1.setDetail(message);
			  m1.setSummary(message);
			  msgs.add(m1);
		  }
	  }

	@Override
	public void validateFile(FacesContext ctx, UIComponent comp, Object value) throws Exception {
		
		 Part file = (Part)value;
		 this.msgs = new ArrayList<FacesMessage>();
		 
		this.files = ScreenPlayAvardsUtil.getAllParts(file);
		
		for(Part fle:this.files){
			validate(fle);
		}

		if (!msgs.isEmpty()) {
		    throw new ValidatorException(msgs);
		}
		
	}
	
	private void validate(Part file){
		long fsze = file.getSize();
		 String contentType = file.getContentType();
		 String fileName = file.getSubmittedFileName();
		 
		 if (fsze > this.maxFileSize.longValue()) {
		    List<String> messages = new ArrayList<String>(0);
			messages.add("File "+fileName+" is too big.");
			messages.add("Max. alowed file size is 25 MB.");
			this.addErrorMessages(messages);
		  }
		
		  if (!this.allowedContentTypes.contains(contentType)) {
			  List<String> messages = new ArrayList<String>(0);
			  messages.add(fileName+"'s content type " + contentType + " is not allowed.");
			  messages.add("Alowed file types are: *.pdf, *.doc, *.xdoc, *.txt");
			  this.addErrorMessages(messages);
		  }
	}

	@Override
	public void save() {
		//Use DAO to save to database;???
//		try (InputStream input = file.getInputStream()) {
//	        //Files.copy(input, new File(uploads, filename).toPath());
//	    }
//	    catch (IOException e) {
//	        // Show faces message?
//	    }
	}

}
