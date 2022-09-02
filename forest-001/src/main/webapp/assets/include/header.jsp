<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>${param.title}</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">
  
  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="assets/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="assets/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="assets/lib/prettyphoto/css/prettyphoto.css" rel="stylesheet">
  <link href="assets/lib/hover/hoverex-all.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
    Template Name: Solid
    Template URL: https://templatemag.com/solid-bootstrap-business-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>

  <!-- Fixed navbar -->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        <a class="navbar-brand" href="index.jsp">HAPPY HEART</a>
      </div>
      <div class="navbar-collapse collapse navbar-right">
        <ul class="nav navbar-nav">
          <li><a href="index.jsp">HOME</a></li>
          <li class="active"><a href="about.jsp">ABOUT</a></li>
          <li><a href="contact.jsp">CONTACT</a></li>
          <li><a href="${pageContext.request.contextPath}/home?action=viewGroups">CLASSES</a></li>
       


<c:choose>

  <c:when test="${SignedTeamUser != null }">
  <c:set var = "forestuser" scope = "session" value = "${SignedTeamUser.getFirstName()}"/>
  <c:import url="/assets/include/teamnavbar.jsp"><c:param name="mytitle" value="Welcome ${forestuser}"></c:param></c:import>   
  </c:when>
  
  <c:when test="${SignedUser != null }">
  <c:set var = "forestuser" scope = "session" value = "${SignedUser.getFirstName()}"/>
  <c:import url="/assets/include/customernavbar.jsp"><c:param name="mytitle" value="Welcome ${forestuser}"></c:param></c:import>   
  </c:when>
  
  <c:otherwise>
  <c:set var = "forestuser" scope = "session" value = "ACCOUNT"/>
  <c:import url="/assets/include/pagesnavbar.jsp"><c:param name="mytitle" value="${forestuser}"></c:param> </c:import>  
  </c:otherwise>

</c:choose>

        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>