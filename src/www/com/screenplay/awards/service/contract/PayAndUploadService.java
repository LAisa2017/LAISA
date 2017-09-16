/**
 * 
 */
package www.com.screenplay.awards.service.contract;

import www.com.screenplay.awards.bo.PayAndUploadBo;
import www.com.screenplay.awards.fileupload.ScreenPlayFileUploadImpl;

/**
 * @author sarans
 *
 */
public interface PayAndUploadService {
	 void upload();
	 Double getSubmissionPrice(String scriptType);
	 PayAndUploadBo getPayAndUploadBusinessObject();
	 ScreenPlayFileUploadImpl getScreanPlayFileUpload();
	 String[] getDataToSubstitute();
}
