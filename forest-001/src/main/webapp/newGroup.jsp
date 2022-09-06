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

        
          <div class="form-inline input-group-addon">
          
           <label for="class_type">SELECT CATEGORY: </label>
           
            <select name="class_type" id="category_list" class="form-control"
            form="create_group_form">
              <c:forEach items="${listCategory}" var="category" >

                  <option  value="${category.name}">${category.name}</option>

              </c:forEach>
            </select>
            
	           <label for="new_class_type">If other, please specify: </label>
	           <input type="text" name="new_class_type" 
	           placeholder="Please specify new category" 
	           form="create_group_form"
	           class="form-control">
	           
          </div>
        
        <form name="create_group_form" id="create_group_form" class="form white-text" action="<%= request.getContextPath()%>/classes?action=createGroup" method="POST">
    
				<div class="form-group">
				<label for="className">NAME:</label> 
						<input type="text" name="className"
							class="form-control" 
							required="required" 
							placeholder="Enter your class name">
						<div class="validate"></div>
					</div>

	        <div class="form-group">
	        <label for="description">DESCRIPTION:</label> 
	              <textarea class="form-control" name="description"
	              required="required" 
	              placeholder="Description of Class" rows="5"
	              autocapitalize="sentences" 
	              ></textarea>
	        <div class="validate"></div>
	        </div>
	        
	        <div class="form-group">
	        <label for="image">PICTURE:</label> 
            <select name="image" id="image_list" class="form-control">
              <option ></option>
              <c:forEach items="${listImages}" var="category" >
                <option  value="${listImages.name}">${listImages.name}</option>
              </c:forEach>
            </select>
	              <img alt="default.jpg" src="assets/img/uploaded/default.jpg" height="60">
	              
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
