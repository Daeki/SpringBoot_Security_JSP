<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>

<!-- 절대 경로  -->
<!-- 파라미터 넘겨줌  안넘기면 error -->
<c:import url="../template/layout_head.jsp">
	<c:param name="titleName" value="MemberLogin"></c:param>
</c:import>
<body>
<c:import url="../template/layout_header.jsp"></c:import>


<div class="container mt-2" >
		<h2 class="mt-4">Member Login Page</h2>
		<h2>authentication : ${SPRING_SECURITY_CONTEXT}
		</h2>
		
		<hr>
		<div th:if="${param.error}">
			<c:if test="${not empty LoginFailMessage}">
           <p class="error-message">ERROR????? ${LoginFailMessage}</p>
           </c:if>
           
           <h3>param.error : ${param.error==true}</h3>
           <c:if test="${param.error}">
           		<h3>Login Error !!!! </h3>
           </c:if>
           
        </div>
		
	    <div th:if="${param.error}">
            Invalid username and password.
        </div>
        <div th:if="${param.logout}">
            You have been logged out.
        </div>
        <hr>
		<form th:action="@{/member/memberLogin}" action="memberLogin" method="post">
 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="form-group">
				<label for="id">ID</label> 
				<input type="text" class="form-control" id="id" name="username"
					aria-describedby="idlHelp" value="user1"> 
					<small id="idlHelp"	class="form-text text-muted">
						We'll never share your email with anyone else.
					</small>
			</div>
			<div class="form-group">
				<label for="pw">Password</label> 
				<input type="password" class="form-control" id="pw" name="password" value="user1">
			</div>
			<div class="form-group form-check">
			    <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
			    <label class="form-check-label" for="remember-me">Remember me?</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
		
	</div>
    
<c:import url="../template/layout_footer.jsp"></c:import>
</body>
</html>