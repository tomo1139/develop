function cancelRegist() {
	objDate = new Date();
	y = objDate.getFullYear();
	m = objDate.getMonth()+1;
	d = objDate.getDate();
	h = objDate.getHours();
	min = objDate.getMinutes();
	document.registForm.datetime.value = y + "/" +
										 ("0" + m).substr(-2) + "/" +
										 ("0" + d).substr(-2) + " " +
										 ("0" + h).substr(-2) + ":" + 
										 ("0" + min).substr(-2);
	document.registForm.means.selectedIndex=0;
	document.registForm.opponent.selectedIndex=0;
	document.registForm.status.selectedIndex=0;
	document.registForm.detail.value = "";
}

function clickTodayPulldownBtn() {
    var elems = document.getElementsByName("todayVisitPullDownBtnName");

    var s = elems[0].src;
    
    if(s.indexOf("showPulldown.gif") == -1) {
    	$("#todayMainContents").slideUp("normal");
    	elems[0].src = "./css/img/showPulldown.gif";
    } else {
    	$("#todayMainContents").slideDown("normal");
    	elems[0].src = "./css/img/hidePulldown.gif";
    }
}

function clickTodayPulldownBtnUnreg() {
    var elems = document.getElementsByName("todayVisitPullDownBtnUnregName");

    var s = elems[0].src;
    
    if(s.indexOf("showPulldown.gif") == -1) {
    	$("#todayUnregMainContents").slideUp("normal");
    	elems[0].src = "./css/img/showPulldown.gif";
    } else {
    	$("#todayUnregMainContents").slideDown("normal");
    	elems[0].src = "./css/img/hidePulldown.gif";
    }
}

function Sleep( T ){ 
   var d1 = new Date().getTime(); 
   var d2 = new Date().getTime(); 
   while( d2 < d1+T ){
       d2=new Date().getTime(); 
   } 
   return; 
} 
