/**
 * 
 */

$(window).ready(function() {

			//maxWindow();

			renderPayPalButton('#paypal-button');
			initializeAlerts();
			if(isError()){
				toggle(formId + "uploadFileAjax");
				//updateTextBorders();				
			}
			var message = isSuccess();
			if(message  != null){
				//alert(message);
				//clearForm();
				window.location.href = "/screenplayawards/completed/screenPlayUploadCompleted.jsf";										 
			}
			
			
			
		});