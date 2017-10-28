<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/loadingBar/spin.js" />
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>New Document</TITLE>



</HEAD>

<BODY id="foo">

	<button type="button" id="one" onclick="startSpinner()">hi</button>

	<button type="button" id="two" onclick="stopSpinner()">2</button>

</BODY>
</HTML>

<script>

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
 
    var target = document.getElementById('foo');
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
</script>



