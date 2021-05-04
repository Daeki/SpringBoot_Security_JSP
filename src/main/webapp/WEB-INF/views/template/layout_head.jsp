<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

    <!-- Required meta tags -->
   <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <title th:text="${title}">${param.titleName}</title>
	
	<c:url var="url" value="${pageContext.request.contextPath}"></c:url>
	
	<!-- th의 url 주소는 다음과 같은 형식이면 절대경로와 상대 경로 둘다 가능 -->
	<link th:href="@{/css/default.css}" rel="stylesheet" href="${url}/css/default.css">    
	<!-- Custom styles for this template -->
	<link th:href="@{/css/starter-template.css}" href="${url}/css/starter-template.css" rel="stylesheet">
	
	</head>