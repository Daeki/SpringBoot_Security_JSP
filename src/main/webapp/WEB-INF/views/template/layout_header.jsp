<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" th:fragment="header">
	  <a class="navbar-brand" href="${url}/" th:href="@{/}">Navbar</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="${url}/" th:href="@{/thymeleaf}">HOME <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="${url}/notice/list" th:href="@{/notice/list}">Notice</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="${url}/qna/list" th:href="@{/qna/list}">QnA</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
	        <div class="dropdown-menu" aria-labelledby="dropdown01">
	          <a class="dropdown-item" href="#">Action</a>
	          <a class="dropdown-item" href="#">Another action</a>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
	    </ul>
	   <sec:authorize access="isAuthenticated()">
	     <ul class="navbar-nav mr-0" >
	      <li class="nav-item" ><a href="${url}/member/memberPage" class="nav-link" th:href="@{/member/memberPage}"><i style="margin-right:5px;" class='fas fa-user-plus'></i>My</a></li>
	      <li class="nav-item" ><a href="${url}/member/memberLogout" class="nav-link" th:href="@{/member/memberLogout}"><i style="margin-right:5px;" class='fas fa-user-lock'></i>Logout</a></li>
	    </ul>
	    </sec:authorize>
	    
<%-- 	   <sec:authorize access="isAnonymous()">
		<ul class="navbar-nav mr-0" >
	      <li class="nav-item"><a  href="${url}/member/memberJoin" class="nav-link" th:href="@{/member/memberJoin}"><i style="margin-right:5px;" class='fas fa-user-plus'></i>Sign Up</a></li>
	      <li class="nav-item"><a  href="${url}/member/memberLogin" class="nav-link" th:href="@{/member/memberLogin}"><i style="margin-right:5px;" class='fas fa-user-lock'></i>Login</a></li>
	    </ul>
	    </sec:authorize> --%>
	    <c:if test="${empty SPRING_SECURITY_CONTEXT}">
	    <ul class="navbar-nav mr-0" >
	      <li class="nav-item"><a  href="${url}/member/memberJoin" class="nav-link" th:href="@{/member/memberJoin}"><i style="margin-right:5px;" class='fas fa-user-plus'></i>Sign Up</a></li>
	      <li class="nav-item"><a  href="${url}/member/memberLogin" class="nav-link" th:href="@{/member/memberLogin}"><i style="margin-right:5px;" class='fas fa-user-lock'></i>Login</a></li>
	    </ul>
	    </c:if>
	  </div>
	</nav>