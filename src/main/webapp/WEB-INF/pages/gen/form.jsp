<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/decorators/common/mostOfHead.jsp"%>
    <title>Wsdl2html</title>
</head>

<body>
	<%@ include file="/decorators/common/topNav.jsp"%>

	<div class="container">
		<%@ include file="/decorators/common/errors.jsp"%>
		<h2 class="text-center" style="padding-top: 100px">Convert WSDL
			to Readable HTML</h2>
		<form:form class="form-horizontal" id="form"
			action="${pageContext.request.contextPath}/doGen" method="post"
			commandName="command" enctype="multipart/form-data">			
			<div class="form-group">

				<div class="form-group" style="padding-top: 60px">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" path="wsdlUrl"
							placeholder="Input a wsdl url" onClick="clearUrlFieldIfNeeded()"
							id="wsdlUrl" />
					</div>
					<div class="col-sm-2">
						<a href="${pageContext.request.contextPath}/help/faq#intranet">WSDL
							Private?</a>
					</div>
				</div>

				<div class="form-group" style="padding-top: 10px">
					<div class="text-center">
						<button type="submit" class="btn btn-primary btn-lg">Convert to HTML</button>
					</div>
				</div>
		</form:form>


		<div style="padding-top: 60px">
			<hr />
			 
				<div class="text-center">© 2016 Wsdl2html</div>
		 
		</div>
	</div>


	<%@ include file="/decorators/common/js.jsp"%>

	<script>
		var default_wsdl = '<c:out value="${command.defaultWsdl}"/>';

		function clearUrlFieldIfNeeded() {
			var field = document.getElementById("wsdlUrl");
			if (field.value == default_wsdl) {
				field.value = "";
			}
		}
	</script>
	
	
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-60175901-2', 'auto');
	  ga('send', 'pageview');
	
	</script>	

</body>
</html>








