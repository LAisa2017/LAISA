var handler = StripeCheckout.configure({
  key: 'pk_test_6pRNASCoBOKtIshFeQd4XMUh',
  image: '../resources/images/ff-laisa-logo6.jpg',
  locale: 'auto',
  token: paymentConfirmed
 
});



document.getElementById(formId+'customButton').addEventListener('click', onCustomButtonClick);


// Close Checkout on page navigation:
window.addEventListener('popstate', popStateFunction);
function popStateFunction(){
	  handler.close();
}

/*function paymentConfirmed(token){//token is send after sucessful payment
	var tokenStr = JSON.stringify(token);
	document.getElementById(formId+"token").value = tokenStr;
	var paymentAccepted = true;
	if(paymentAccepted){
		doUpload();
	}else{
		var msg = "Ups. Something went wrong your payment has not ben accepted.\Please try again.";
	}
	
	
}*/

/*function doUpload(){
	var upFile = document.getElementById(formId+'file').value;
	if(upFile == '' || upFile === undefined){
		var msg = "Congratulations your payment has ben accepted.\n";
			msg = msg + "However you did not select file to upload.\n";
			msg = msg + "Please click OK then select file and click upload button";
		alert(msg);
		toggle(formId+'uploadFileAjax');
	}else{
		alert("Congratulations your payment has ben accepted click OK to upload selected script;");
		var button = document.getElementById(formId+'uploadFileAjax');
		button.click();
	}
}*/

function onCustomButtonClick(e){
	var email = document.getElementById(formId+'e-Mail').value;
	var stripeCheckout = {
		name: 'ScreenPlayAwards.com',
		description: 'LA Screen Play Awards',
		zipCode: false,
	    amount: (amountVal*100),
	    email: email
	    
	}
	 handler.open(stripeCheckout);
	if(event){
		//alert("Prevent default event");
		event.preventDefault();
	}else{
		//alert("Prevent default e");
		e.preventDefault();
	}
     return false;
}