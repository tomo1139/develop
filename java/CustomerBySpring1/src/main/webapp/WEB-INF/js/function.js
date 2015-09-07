$(window).on('scroll', function(event){
	
	var delem = document.documentElement;
	var dbody = document.body;
	
	scroll = (delem.scrollTop || dbody.scrollTop);
	
	if(scroll == '0') {
		$('#jumpButton').fadeOut("fast");
	} else {
		$('#jumpButton').fadeIn("fast");
	}
}); 

function jumpToTop() {
    $('body, html').animate({scrollTop: 0}, 300, 'linear');
}

function nowloading() {
     $.blockUI({ overlayCSS: { backgroundColor: '#444' } }); 
	/*
     $.blockUI(
    		 {
    			 message: '<p><img src="./css/img/loading.gif" style="vertical-align:middle;" /> 読み込み中...</p>'
    		 }
     );
     */
}

function nowloadingOff() {
     $.unblockUI;
}

function loginButtonClicked() {
	$("#loginContainer").fadeout();
}

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

function showMap(address) {
	var markTitle = address;

	var contentString = '<div id="content" style="width: 250px; height: 80px;">'+ address+ '</div>';

	var geocoder = new google.maps.Geocoder();

	geocoder.geocode(
	{
	    'address': address,
	    'region': 'jp'
	}, function(results, status){
		if(status==google.maps.GeocoderStatus.OK){
			var myOptions = {
					zoom: 16,
					center: results[0].geometry.location,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					scrollwheel: false
			}

			var map = new google.maps.Map(document.getElementById("gmap"), myOptions);

			var infowindow = new google.maps.InfoWindow({ content: contentString });

			var marker = new google.maps.Marker({
				position: results[0].geometry.location,
				map: map,
				title: markTitle
			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map,marker);
			});
	    }
		else {
			alert('invalid address\n' + address);
		}
	  }
	);
}

function createMap(address) {
	window.open('map.jsp', '_blank',
				'width=610, height=410, menubar=no, toolbar=no, scrollbars=no, status=no, resizable=no resizable=0');
}

function checkLoginPass() {
    var forms = document.getElementById("loginFormId");
    var array = [];
    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];
        if(elem.name == 'pass') {
        	if(elem.value=='' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("passErrorJS").innerHTML = "";
        	}
        	else {
        		document.getElementById("passErrorJS").innerHTML = "パスワードは半角英数で入力して下さい";
        	}
        }
    }
}

function checkRegistInput() {
    var forms = document.getElementById("registNegoFormId");
    var array = [];
    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];

        if(elem.name == 'detail') {
        	var str = elem.value;
        	allCharNum = getAllCharNum(str);

       		document.getElementById("countDetailCharNum").innerHTML = "(" + allCharNum + "/ 32)";
        	
        	if(allCharNum > 32) {
        		var oldStr;
        		for(var idx=0; idx<str.length+1; idx++) {
        			var tmp = str.substr(0, idx);
        			num = getAllCharNum(tmp);
        			
        			if(num > 32) {
        				elem.value = oldStr;
        				break;
        			} else {
        				oldStr = tmp;
        			}	
        		}

        		if(str.slice(-1) == '\n') {
        			if(allCharNum == 33) {
        				document.getElementById("countDetailCharNum").innerHTML = "(" + 31 + "/ 32)";
        			}
        			else {
        				document.getElementById("countDetailCharNum").innerHTML = "(" + 32 + "/ 32)";
        			}
        		}
        		else {
        			document.getElementById("countDetailCharNum").innerHTML = "(" + 32 + "/ 32)";
        		}
        		document.getElementById("registDetailErrMsgJS").innerHTML = "32文字以下で入力して下さい";
        	}
        	else {
        		document.getElementById("registDetailErrMsgJS").innerHTML = "";
        	}
        }
    }
}

function getAllCharNum(str) {
  	var lineNumMatch = str.match(/\r\n|\n/g);
    var removeNewLineStr = str.replace(/\r\n|\n/g, "");
        	
    var lineNum = 0;
    if(lineNumMatch != null) {
    	lineNum = lineNumMatch.length;
    }
        	
    var charNum = 0;
    if(removeNewLineStr != null) {
    	charNum = removeNewLineStr.length;
    }
        	
    var allCharNum = lineNum*2 + charNum;
    
    return allCharNum;
}

function checkDetailInput() {
    var forms = document.getElementById("registFormId");
    var array = [];
    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];

        if(elem.name == 'detailEdit') {

        	var str = elem.value;
        	allCharNum = getAllCharNum(str);

       		document.getElementById("countDetailCharNum").innerHTML = "(" + allCharNum + "/ 32)";
        	
        	if(allCharNum > 32) {
        		var oldStr;
        		for(var idx=0; idx<str.length+1; idx++) {

        			var tmp = str.substr(0, idx);
        			num = getAllCharNum(tmp);
        			
        			if(num > 32) {
        				elem.value = oldStr;
        				break;
        			} else {
        				oldStr = tmp;
        			}
        		}
        		
        		if(str.slice(-1) == '\n') {
        			if(allCharNum == 33) {
        				document.getElementById("countDetailCharNum").innerHTML = "(" + 31 + "/ 32)";
        			}
        			else {
        				document.getElementById("countDetailCharNum").innerHTML = "(" + 32 + "/ 32)";
        			}
        		}
        		else {
       				document.getElementById("countDetailCharNum").innerHTML = "(" + 32 + "/ 32)";
        		}
  				document.getElementById("registDetailErrMsgJS").innerHTML = "32文字以下で入力して下さい";
        	}
        	else {
        		document.getElementById("registDetailErrMsgJS").innerHTML = "";
        	}
        }
    }
}

function getElem(name) {
    var forms = document.getElementById("registFormId");

    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];

        if(elem.name == name) {
        	return elem;
        }
    }
}

function checkLogin() {
    var forms = document.getElementById("loginFormId");
    var array = [];
    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];
        array.push(elem.name + '=' + elem.value);
        if(elem.name == 'name') {
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("nameErrorLoginJS").innerHTML = "";
        	} else {
        		document.getElementById("nameErrorLoginJS").innerHTML = "名前は半角英数で入力して下さい";
        	}
        } else if(elem.name == 'pass') {
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("passErrorJS").innerHTML = "";
        	} else {
        		document.getElementById("passErrorJS").innerHTML = "パスワードは半角英数で入力して下さい";
        	}
        }
    }
}

function checkRegist() {
	document.getElementById("successMsg").innerHTML = "";
	document.getElementById("errorMsg").innerHTML = "";

    var forms = document.getElementById("loginFormId");
    var array = [];
    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];
        array.push(elem.name + '=' + elem.value);
        
        var name;
        var pass;
        var pass2;
        
        if(elem.name == 'name') {
        	name = elem.value;
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("nameErrorJS").innerHTML = "";
        	} else {
        		document.getElementById("nameErrorJS").innerHTML = "名前は半角英数で入力して下さい";
        	}
        } else if(elem.name == 'pass') {
        	pass = elem.value;
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("passErrorNewJS").innerHTML = "";
        	} else {
        		document.getElementById("passErrorNewJS").innerHTML = "パスワードは半角英数で入力して下さい";
        	}
        } else if(elem.name == 'pass2') {
        	pass2 = elem.value;
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("passErrorJS2").innerHTML = "";
        		if(pass != pass2) {
        			if(pass2 != '') {
        				document.getElementById("passErrorJS2").innerHTML = "パスワードが一致しません";
        			}
        		}
        	} else {
        		document.getElementById("passErrorJS2").innerHTML = "確認用パスワードは半角英数で入力して下さい";
        	}
        }
    }
}

function checkChangePass() {
	document.getElementById("successMsg").innerHTML = "";
	document.getElementById("errorChangePassMsg").innerHTML = "";
	
    var forms = document.getElementById("changePassFormId");
    var array = [];
    for (var i = 0; i < forms.length; i++) {
        var elem = forms.elements[i];
        array.push(elem.name + '=' + elem.value);
        
        var oldPass;
        var newPass;
        var newPass2;
        
        if(elem.name == 'oldPass') {
        	oldPass = elem.value;
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("oldPassErrorJS").innerHTML = "";
        	} else {
        		document.getElementById("oldPassErrorJS").innerHTML = "旧パスワードは半角英数で入力して下さい";
        	}
        } else if(elem.name == 'newPass') {
        	newPass = elem.value;
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("newPassErrorJS").innerHTML = "";
        	} else {
        		document.getElementById("newPassErrorJS").innerHTML = "新パスワードは半角英数で入力して下さい";
        	}
        } else if(elem.name == 'newPass2') {
        	newPass2 = elem.value;
        	if(elem.value == '' || elem.value.match(/^[0-9a-zA-Z_]+$/)) {
        		document.getElementById("newPassErrorJS2").innerHTML = "";
        		if(newPass != newPass2) {
        			if(newPass2 != '') {
        				document.getElementById("newPassErrorJS2").innerHTML = "パスワードが一致しません";
        			}
        		}
        	} else {
        		document.getElementById("newPassErrorJS2").innerHTML = "新パスワードは半角英数で入力して下さい";
        	}
        }
    }
}

var state;
var srcX;
var srcY;
var srcW;
var srcH;
var dstX;
var dstY;
var cnt = 0;

const NORMAL=0;
const EXPLODE=1;
const END=2;
const WAIT=3;

function createBomb(id) {
	var bomb1 = new Bomb(id);

	img.onload = function() {
		var timer = setInterval(MainLoop, 50);
	}
}

var MainLoop = function(obj) {
	ctx.clearRect(0, 0, 64, 64);

	cnt++;
	
	if     (state == NORMAL){ shakeBombNormal(); }
	else if(state == WAIT)  { shakeBombWait(); }

	switch(state) {
		case NORMAL: normal(); break;
		case EXPLODE: explode(); break;
		case END: end(); break;
		case WAIT: break;
		default: break;
	}

	ctx.drawImage(img,
				  srcX, srcY, srcW, srcH,
				  dstX, dstY, srcW, srcH);
}

Bomb.prototype.onMouseDownBomb = function() {
	state = EXPLODE;
	srcX = 0;
	srcY = srcH;
	cnt = 0;
	dstX = 0;
	dstY = 0;
}

Bomb.prototype.onMouseMoveBomb = function() {
	if(state == NORMAL) {
		state = WAIT;
		cnt = 0;
		srcW = 64;
		srcH = 64;
		srcX = srcW*5;
		srcY = 0;
	}
}
	
Bomb.prototype.onMouseOutBomb = function() {
	if(state == WAIT) {
		state = NORMAL;
		srcX = 0;
		srcY = 0;
		srcW = 64;
		srcH = 64;
		cnt = 0;
	}
}

var shakeBombNormal = function() {
	if(cnt % 2 == 1) {
		dstX = parseInt(Math.random() * 3) - 1;
		dstY = parseInt(Math.random() * 3) - 1;
	}
}

var shakeBombWait = function() {
	dstX = parseInt(Math.random() * 5) - 2;
	dstY = parseInt(Math.random() * 5) - 2;
}

var normal = function() {
	if(cnt % 20 < 10) {
		srcX = srcW*1;
	} else {
		srcX = srcW*2;
	}
	srcY = 0;
}

var explode = function() {
	if(cnt % 2 == 0) {
		srcX += srcW;
		if(srcX == srcW*5) {
			state = END;
			cnt = 0;
			srcW = 0;
			srcH = 0;
		}
	}
}

var end = function() {
	if(cnt == 30) {
		state = NORMAL;
		srcX = 0;
		srcY = 0;
		srcW = 64;
		srcH = 64;
		cnt = 0;
	}
}

var canvas;
var ctx;
var img;

function Bomb(id) {

	canvas = document.getElementById(id);
	ctx = canvas.getContext('2d');

	state = NORMAL;
	
	srcX = 0;
	srcY = 0;
	srcW = 64;
	srcH = 64;
	dstX = 0;
	dstY = 0;
	
	canvas.addEventListener('mousedown', this.onMouseDownBomb, false);
	canvas.addEventListener('mousemove', this.onMouseMoveBomb, false);
	canvas.addEventListener('mouseout', this.onMouseOutBomb, false);
	
	img = new Image();
	img.src = "./css/img/bomb.png";
}
