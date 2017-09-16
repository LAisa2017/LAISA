/**
 * 
 */

function goToScreenPlayCheckout(elementId){
	
	var scriptType0 = document.getElementById(elementId+":0");
	var scriptType1 = document.getElementById(elementId+":1");
	var scriptType2 = document.getElementById(elementId+":2");
	var scriptTypeSelected = "";
	if(scriptType0.checked)scriptTypeSelected = scriptType0.value;
	if(scriptType1.checked)scriptTypeSelected = scriptType1.value;
	if(scriptType2.checked)scriptTypeSelected = scriptType2.value;
	
	var url = "/screenplayawards/checkout/screenPlayCheckout.jsf?scriptType="+scriptTypeSelected;
	
	window.location.href = url;
	
}