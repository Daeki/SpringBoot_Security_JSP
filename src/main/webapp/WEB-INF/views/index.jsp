<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>

<!-- 절대 경로  -->
<!-- 파라미터 넘겨줌  안넘기면 error -->
<c:import url="./template/layout_head.jsp">
	<c:param name="titleName" value="Index"></c:param>
</c:import>
<body>

<c:import url="./template/layout_header.jsp"></c:import>


	<div class="container">
		<h1> Index JSP Page </h1>
		<a href="${url}/notice/list">list</a>
		<h1>authentication : ${sessionScope.SPRING_SECURITY_CONTEXT.authentication}</h1>
	</div>
    


        
<c:import url="./template/layout_footer.jsp"></c:import>
</body>
</html>