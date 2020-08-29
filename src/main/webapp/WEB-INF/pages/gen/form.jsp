<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/decorators/common/mostOfHead.jsp"%>
    <title>wsdl2html</title>
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

					<div class="col-sm-12">
						<form:input type="text" class="form-control" path="wsdlUrl"
							placeholder="${command.inputPlaceholder}"
							id="wsdlUrl" />
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
			 
				<div class="text-center">wsdl2html</div>
		 
		</div>
	</div>


	<%@ include file="/decorators/common/js.jsp"%>


</body>
</html>








