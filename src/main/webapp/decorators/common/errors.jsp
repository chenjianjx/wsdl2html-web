<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${errors != null && not empty errors}">
	 
		<div class="row alert alert-danger">
			<c:forEach items="${errors}" var="error">
				<div><c:out value="${error.msg}"/></div>
				<c:if test="${not empty error.detail}">
					<pre>
						<c:out value="${error.detail}"/>					 											 	
					</pre>				
				</c:if>
				
			</c:forEach>	
		</div>
		
	 
</c:if>

