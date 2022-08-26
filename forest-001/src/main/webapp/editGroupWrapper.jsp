<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kukvar.hibernate.entity.Files" %>
<%@ page import="com.kukvar.hibernate.entity.Group" %>
<%@ page import="java.util.List" %>
  <!-- *****************************************************************************************************************
   CLASSES WRAPPER
   ***************************************************************************************************************** -->

  <div class="container mtb">
    <div class="row centered">
      <h3 class="mb">Group classes include Yoga variations, Mat Pilates, Cycle, TRX, and Zumba!</h3>

<%!String nameClass, description, nameImageFile;
  int classId;%>
<%
String path = (String) request.getAttribute("path");

//List<Files> files = (List<Files>) request.getAttribute("files");
List<Group> groups = (List<Group>) request.getAttribute("groups");
%> 

<!-- *****************************************************************************************************************
   OUR GROUPS
***************************************************************************************************************** -->

		 <c:forEach var='group' items='${groups}'>
		    <jsp:include page='editedGroup.jsp'>
		      <jsp:param name='groupId' value='${group.getId()}'></jsp:param>
		      <jsp:param name='name' value='${group.getName()}'></jsp:param>
		      <jsp:param name='description' value='${group.getDescription()}'></jsp:param>
		      <jsp:param name='nameImageFile' value='${group.getNameImageFile()}'></jsp:param> 
		      <jsp:param name='path' value='${path}'></jsp:param> 
		    </jsp:include>
		 </c:forEach>

<!-- *****************************************************************************************************************
   CREATE NEW CLASS BUTTON
***************************************************************************************************************** -->
  
      <p><br/><a href="${pageContext.request.contextPath}/classes?action=createGroup" class="btn btn-theme">CREATE NEW CLASS</a></p>

    </div>
  </div>
  