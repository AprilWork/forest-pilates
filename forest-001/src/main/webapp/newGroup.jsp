<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url="assets/include/header.jsp"><c:param name="title" value="Happy Heart"/></c:import>


  <!-- *****************************************************************************************************************
   BLUE WRAP
   ***************************************************************************************************************** -->
  <div id="blue">
    <div class="container">
      <div class="row">
        <h3>CREATE NEW CLASS.</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
  </div>
  <!-- /blue -->

  <div class="container mtb">
    <div class="row">
      <div class="col-lg-8">
        <h4></h4>
        <div class="hline"></div>
			<p></p>
			<!-- Register Form -->
        <form class="form white-text" action="<%= request.getContextPath()%>/classes?action=createGroup" method="POST">
				
					<div class="form-group">
						<input type="text" name="className"
							class="form-control" required="required" placeholder="Enter your class name"
							data-rule="minlen:4" data-msg="Please enter at least 4 chars">
						<div class="validate"></div>
					</div>

	        <div class="form-group">
	              <textarea class="form-control" name="description" 
	              required="required" placeholder="Description of Class" rows="5"
	              autocapitalize="sentences" 
	              data-rule="required" data-msg="Please write something about your class"></textarea>
	        <div class="validate"></div>
	        </div>

				<div class="form-group">
					<input type="file" name="files" class="form-control"
					  
						placeholder="Enter your image file name">
						
					<div class="validate"></div>
				</div>


				<div class="loading"></div>
          <div class="error-message"></div>
            
          <div class="form-send">
            <button type="submit"  class="btn btn-theme btn-white mt30">REGISTER</button>
          </div>
          
          <div class="form-group">
            <br/>
            <a href="<%= request.getContextPath()%>/classes?action=listingGroups" style="form-control">Cancel ></a>          
          </div> 
          
        </form>			
			
      <!-- /Register Form -->
      
      <!-- Image Upload Form -->
				<form class="form white-text" action="classes?action=filesUpload" method="get" enctype="multipart/form-data">
				Select image <input type="file" name="files" placeholder="Enter your image file name">/>
				<input type="hidden" name="action" value="filesUpload" >
				<input type="submit" value="upload" />
				</form>
				<br/>      
      <!-- /Image Upload Form -->
		</div>
	</div>
</div>

<c:import url="assets/include/footer.jsp"></c:import>
