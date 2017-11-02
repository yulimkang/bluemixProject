<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>New Document</TITLE>



</HEAD>

<BODY id="foo">

 ${date.nowDate}

  ${date.nowTime}
  
  
 <div class="jumbotron">
  <table>
<h1>hi</h1>
  </table>
</div>
  

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



