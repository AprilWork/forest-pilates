  <!-- navbar for team member-->
<li class="dropdown"><a href="#" class="dropdown-toggle"
	data-toggle="dropdown">${param.mytitle}<b class="caret"></b></a>
	<ul class="dropdown-menu">
		<li><a
			href="${pageContext.request.contextPath}/FilesHandler?action=uploadImages">UPLOAD
				IMAGES</a></li>
		<li><a
			href="${pageContext.request.contextPath}/FilesHandler?action=listingImages">VIEW
				AND UPDATE IMAGES</a></li>
		<li><a href="${pageContext.request.contextPath}/classes?action=createGroup">CREATE NEW CLASS</a></li>		
		<li><a href="${pageContext.request.contextPath}/classes?action=listingGroups">VIEW AND EDIT CLASSES</a></li>				
		<li><a href="${pageContext.request.contextPath}/team?action=logout">LOGOUT</a></li>
		
	</ul></li>
