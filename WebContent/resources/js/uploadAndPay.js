/**
 * 
 */
var formId = "formUploadPay:";
var idsToClean = ["Firstname","LastName","e-Mail","Phone","Address","City","Province","Postal","Country","ScreenPlayTitle","Genre","Logline"];

function addScriptReview(chkBox){
	var extra = parseFloat("25.00");
	var discaunt = parseFloat("0.50");
	var elm = window.document.getElementById(formId+"totalValue");
	var curtv = parseFloat(elm.value);
	var newtv = parseFloat("0.0");
	var dicauntChck = document.getElementById(formId+"dicauntChck");
	if(chkBox.checked){
		
		if(dicauntChck.checked){
			newtv = curtv + extra * discaunt;
		}else{
			newtv = curtv + extra;
		}
		
	}else{
		if(dicauntChck.checked){
			newtv = curtv - extra * discaunt;
		}else{
			newtv = curtv - extra;
		}
		
	}
	elm.value = newtv;
	updatePaymenttotal();
}


function resubmitingScript(chkBox){

	var rb0 = document.getElementById(formId+"analysisSelection:0");
	var rb1 = document.getElementById(formId+"analysisSelection:1");
	var subElm = document.getElementById(formId+"submissionValue");
	var subVal = subElm.innerText.split("$")[1]; 
	var submissionValue = parseFloat(subVal);
	var analysisValue = 0.00;
	if(rb0.checked){
		analysisValue = parseFloat(rb0.value);
	}
	if(rb1.checked){
		analysisValue = parseFloat(rb1.value);
	}
	var discaunt = parseFloat("0.50");
	var elm = window.document.getElementById(formId+"totalValue");
	if(chkBox.checked){
		elm.value = analysisValue + submissionValue * ( 1 - discaunt);
	}else{	
		elm.value = analysisValue + submissionValue;
	}
	updatePaymenttotal();
	
}

function toggle(id) {
    var element = document.getElementById(id);
    if(element.style.display == 'block') {
      element.style.display = 'none';
    } else {
      element.style.display = 'block'
    }
  }

/*function ajaxEvent(data){
	
	var status = data.status;
	switch(status){
		case "begin":
			// Before AJAX request is sent

		break;
		case "complete":
			// After AJAX request is arrived

		break;
		case "success":
			// After update of HTML DOM based on AJAX response

		break;
	}
	
}*/

/*function fileChanged(elm){
	var parent = elm.parentElement;
	var selection = elm.value;
	if(selection === ""){
		parent.style.color="#FF0000";
	}else{
		parent.style.color="#0000FF";
	}
}*/

function textEntered(textElm){
	var val = null;
	var tag = textElm.tagName.toLowerCase();
	var color = "#0080ff";
	if(tag === "select"){
		val = textElm.options[textElm.selectedIndex].value; 
		if(val === "Select Something"){
			color = "#ff0000";
		}
	}else{
		val =textElm.value;
		if(val == ""){
			color = "#ff0000";
		}
	}
	textElm.style.border="1px solid "+color;//#0080ff";
}

function removeMessage(textId){
	var ulDivElm = document.getElementById("messages");
	var lis = ulDivElm.getElementsByTagName("li");
	for(var i=0; li=lis[i]; i++) {
	    if(li.innerText.indexOf(textId.split(":")[1]) >= 0){
	    	li.parentNode.removeChild(li);
	    	break;
	    }
	}
}

function maxWindow() {
	window.moveTo(0, 0);
    if (document.all) {
	   top.window.resizeTo(screen.availWidth, screen.availHeight);
	} else if (document.layers || document.getElementById) {
	    if (top.window.outerHeight < screen.availHeight || top.window.outerWidth < screen.availWidth) {
	         top.window.outerHeight = screen.availHeight;
	         top.window.outerWidth = screen.availWidth;
	    }
	}
}

function isError(){
	var ulDivElm = document.getElementById("messages");
	var liElmnts = ulDivElm.getElementsByTagName("li");
	if(liElmnts && liElmnts.length > 0){
		updateTextBorders();
		return true;
	}
	return false;
}

function updateTextBorders(){
	for(var i=0; id=idsToClean[i]; i++){
		var textElm = document.getElementById(formId + id);
		alert(textElm.placeholder)
		textEntered(textElm);
		removeMessage(id);
	}
}

function isSuccess(){
	var succElm = document.getElementById(formId+"successMessage");
	if(succElm){
		message = succElm.innerText;
		if(message === undefined || message === ""){
			return null;
		}
		succElm.innerText = "";
		return message;
	}
	return null;
}

function clearForm(){
	
	for(var i=0; i < idsToClean.length; i++){
		var id=idsToClean[i];
		var elm = document.getElementById(formId + id);
		var tagName = elm.tagName.toLowerCase();
		if("select" === tagName){
			elm.selectedIndex = 0;
		}else{
			elm.value = "";
		}
		
	}
}

function scriptAnalysisRequest(analysisSelectionId,source){
	var rb0 = document.getElementById(formId+analysisSelectionId+":0");
	var rb1 = document.getElementById(formId+analysisSelectionId+":1");
	
	if(source.checked){
		rb1.disabled = false;
		rb0.disabled = false;
		rb0.checked = true;
		rb1.checked = false;
		addAnalysisValue(rb0.value);
	}else{
		rb1.disabled = true;
		rb0.disabled = true;
		if(rb0.checked){
			addAnalysisValue("-"+rb0.value);
		}
		if(rb1.checked){
			addAnalysisValue("-"+rb1.value);
		}
		rb0.checked = false;
		rb1.checked = false;
	}
	
}

function addAnalysisValue(value){
	var elm   = window.document.getElementById(formId+"totalValue");
	var curtv = parseFloat(elm.value);
	elm.value = curtv + parseFloat(value);;
	updatePaymenttotal();
}

function addScriptAnalysis(analysisSelectionId){
	var rb0 = document.getElementById(formId+analysisSelectionId+":0");
	var rb1 = document.getElementById(formId+analysisSelectionId+":1");
	var checkedValue = 0.00;
	var uncheckedValue = 0.00;
	if(rb0.checked){
		checkedValue = parseFloat(rb0.value);
		uncheckedValue = parseFloat(rb1.value);
	}
	if(rb1.checked){
		checkedValue = parseFloat(rb1.value);
		uncheckedValue = parseFloat(rb0.value);
	}
	var value =  (checkedValue - uncheckedValue);
	addAnalysisValue(""+value);
}

function doUpload(){
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
}

function paymentConfirmed(token){//token is send after sucessful payment
	var tokenStr = JSON.stringify(token);
	document.getElementById(formId+"token").value = tokenStr;
	var paymentAccepted = true;
	if(paymentAccepted){
		doUpload();
	}else{
		var msg = "Ups. Something went wrong your payment has not ben accepted.\Please try again.";
	}
	
	
}