/**
 * 
 */
function initializeAlerts(){
	var close = document.getElementsByClassName("closebtn");
	var i;

	for (i = 0; i < close.length; i++) {
	    close[i].onclick = function(){
	        var div = this.parentElement;
	        //div.style.display = "none";
	        div.style.opacity = "0";
	        div.style.display = "none";
	        //setTimeout(function(){ div.style.display = "none"; }, 600);
	    }
	}
}


function show(id){
	var div = document.getElementById(id);
	var display = div.style.display;
	if("none" === display){
		div.style.opacity = "1";
	    div.style.display = "block"; 
	}else{
		div.style.opacity = "0";
        setTimeout(function(){ div.style.display = "none"; }, 600);
	}
	 
}