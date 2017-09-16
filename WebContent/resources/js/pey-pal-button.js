

//'use strict';
//exports.__esModule = true;
//	access_token$sandbox$9mzphyz3ftvk3wbz$4e8c2c4940b52ff1840817fd56497520
//exports.default = function (elementRef) {
//	return renderPayPalButton(elementRef);
//};

 /*var button_client = {
    sandbox:    'AbANgVXoRg_PRXsNVgJetvT1UR7rXMrujxwg46rtS8frj_AjYstNbPaCWli_MYWjsPsxJjCCFKKojD19',
	secret:     'EGqqrx212Rxtzj_bT7Ei_eG_2XWAU-DQoeKHbHvMVtWrRs4Dq_RlZ6Umug6dfGGB3Z0swRwaF9cOhXXh'	 
 }*/
var button_client = {
    sandbox:    'AR1asAYuPCxTS2Idvt2nABlRMVv5dpVOH2dDUp_8gfwj4PLyKZrxwZDLpOjNI9et5pTI0PUDSF8Hec4z',
	secret:     'EMdRAzM-qtmImFpBJRTwLk8OcKtnhwfxAKI4cEcq1c1gf6QJCLTGrAyxOHqoxuDiDbsqJUY1VcgMXLrH'	 
 }

 var  button_env = 'sandbox';
 var  button_commit = true;
 var  style = {
     color: 'blue',
     shape: 'rect',
     size: 'small',
   }

 var pay_pal_button_data = {
			env: button_env, // Optional: specify 'sandbox' environment
			client:button_client,
			commit: button_commit, // Optional: show a 'Pay Now' button in the checkout flow
			payment: doPayment,
			onAuthorize: onAuthorize,
			onCancel: onCancel,
			style: style
}
 
 var amountVal = 56.75;
 var currencyVal = 'USD';
 var transactionsObj = {transactions: [{amount: {total: amountVal, currency: currencyVal}}]};
 /*
  * {transactions: [{amount: { total: '2.20', currency: 'USD' }}]}
  */
 function updatePaymenttotal(){
	 var amountElm = window.document.getElementById('formUploadPay:totalValue');
	 amountVal = amountElm.value;
	 transactionsObj = {transactions: [{amount: {total: amountVal, currency: currencyVal}}]};
 }
 function doPayment(){
	 if(amountVal == null || amountVal == ""){
		 window.alert("Please enter amount to be payed");
		 return;
	 }
     var env    = this.props.env;
     var client = this.props.client;

	 var payment = paypal.rest.payment.create(env, client, transactionsObj);
     return payment;
 }
 function onPymentExecute(){
	 // Show a success page to the buyer
	//	alert('Payment Executed');
	 doUpload();
 }
 function onAuthorize(data, actions){
	  // Optional: display a confirmation page here
	 //alert('Payment Authorize data = '+data);
	 paymentConfirmed(data);
     return actions.payment.execute().then(onPymentExecute);
 }
 function onCancel(){
	 alert('The payment was cancelled!');
	 console.log('The payment was cancelled!');
 }
 
 function renderPayPalButton(elementRef){
	 paypal.Button.render(pay_pal_button_data, elementRef);
 }
 
// module.exports = exports["default"];