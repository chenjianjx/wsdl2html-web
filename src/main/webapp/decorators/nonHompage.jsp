<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<%@ include file="/decorators/common/head.jsp"%>

	<body>
		<%@ include file="/decorators/common/topNav.jsp"%>
	
		<div class="container">
			<%@ include file="/decorators/common/errors.jsp"%>
			<decorator:body />
			<hr />
			<footer>
				<div class="text-center">© 2016 Wsdl2html</div>
			</footer>
		</div>
	
	
		<%@ include file="/decorators/common/js.jsp"%>
	
	
	</body>
</html>



