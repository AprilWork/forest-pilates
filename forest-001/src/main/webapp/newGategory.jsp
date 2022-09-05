<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url="assets/include/header.jsp"><c:param name="title" value="Happy Heart"/></c:import>


  <!-- *****************************************************************************************************************
   BLUE WRAP
   ***************************************************************************************************************** -->
  <div id="blue">
    <div class="container">
      <div class="row">
        <h3>CREATE NEW CATEGORY.</h3>
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

        <form class="form white-text" action="<%= request.getContextPath()%>/classes?action=createCategory" method="POST">
    
          <div class="form-group"> 
           <label for="class_type">CATEGORY: </label>

          <input
            type='text' name="class_type" class="form-control"
            required="required" placeholder="Enter category of class"
            autocomplete="off"> 

          <!-- If other, please specify new category: -->
          <div class="validate"></div>
        </div>	
	        

          <div class="form-send">
            <button type="submit"  class="btn btn-theme btn-white mt30">CREATE NEW CLASS</button>
          </div>
                    
        </form>			
			
		</div>
	</div>
</div>

<c:import url="assets/include/footer.jsp"></c:import>
