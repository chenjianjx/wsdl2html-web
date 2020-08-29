<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Login</h1>


<form:form class="form-horizontal" id="form" 
	action="${pageContext.request.contextPath}/auth/login" method="post" commandName="command">
	
 
  
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">User name</label>
    <div class="col-sm-10">
    	 <form:input type="text" class="form-control" path="username" placeholder="Anything"/>
    </div>
  </div>  
  
  
   <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
    	 <form:input type="password" class="form-control" path="password" placeholder="Anything"/>
    </div>
  </div>  
  
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Log In</button>
    </div>
  </div>
</form:form> 

 
