<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image upload form</title>
</head>
<body> 
<form action="FilesHandler?action=filesUpload" method="post" enctype="multipart/form-data">
Select image <input type="file" name="files" multiple/>
<input type="submit" value="upload" />
</form>
<br/>


<a href="${pageContext.request.contextPath}/FilesHandler?action=listingImages">View available images</a> 
</body>
</html>