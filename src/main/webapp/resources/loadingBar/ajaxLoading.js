
document.write("<script src='/resources/loadingBar/spin.js'></script>");

$(document).ready( function() {
	//AJAX 통신 시작
	$(document).ajaxStart(function() {
	    //마우스 커서를 로딩 중 커서로 변경
		startSpinner();
	    $('html').css("cursor", "wait"); 
	});
	//AJAX 통신 종료
	$(document).ajaxStop(function() {
	    //마우스 커서를 원래대로 돌린다
		stopSpinner();
	    $('html').css("cursor", "auto"); 
	});
});


var spinner = null;
function startSpinner() {
    var opts = {
        lines: 12, // The number of lines to draw
        length: 10, // The length of each line
        width: 4, // The line thickness
        radius: 10, // The radius of the inner circle
        color: '#000', // #rgb or #rrggbb
        speed: 1, // Rounds per second
        trail: 60, // Afterglow percentage
        shadow: false// Whether to render a shadow
    };
 
    var target = document.getElementById('htmlBody');
    if (spinner == null) {
        spinner = new Spinner(opts).spin(target);
    }
}
 
function stopSpinner() {
    if (spinner != null) {
        spinner.stop();
        spinner = null;
    }
}