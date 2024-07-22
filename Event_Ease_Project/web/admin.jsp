<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>

<%
	String azzera = request.getParameter("azzeraTutto");
	if(azzera!=null && azzera.compareTo("azzera tuto")==0)
	{
		List<HttpSession> sessioniAttive = (List<HttpSession>)application.getAttribute("sessioni");
		for(HttpSession s : sessioniAttive)
		{
			s.invalidate();
		}
		application.setAttribute("sessioniAttive", new ArrayList<HttpSession>());
		application.setAttribute("operationCounter",0);

	}
%>
</head>
<body>
<%
	List<HttpSession> sessioniAttive = (List<HttpSession>)application.getAttribute("sessioni");
	Integer requestCounter = (Integer) application.getAttribute("operationCounter");
	if(requestCounter == 1000 && sessioniAttive.size()>=10)
	{
		%>
		<form action="#" method="get">
			<input type="submit" name="azzeraTutto" value="azzera tuto">
		</form>
		<% 
	}
%>

</body>
</html>