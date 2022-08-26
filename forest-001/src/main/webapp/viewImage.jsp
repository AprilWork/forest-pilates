<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kukvar.hibernate.entity.Files"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Image</title>
</head>
<body>
<%! Files file; String path; %>  
<% file = (Files) request.getAttribute("file"); 
   path = (String) request.getAttribute("path"); 
%>
File ID: <%= file.getId() %> | File name: <%= file.getFileName() %>
<%
if(file.getLabel() != null) 
	out.print("| Label: "+file.getLabel());
%>
<%
if(file.getCaption() != null) 
  out.print("| Caption: "+file.getCaption());
%>
| <a href="${pageContext.request.contextPath}">Home</a>
| <a href="${pageContext.request.contextPath}/FilesHandler?action=listingImages">List available images</a>
<hr/>
<img src="<%=path+file.getFileName() %>">
</body>
</html>