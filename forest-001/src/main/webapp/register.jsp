<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url="assets/include/header.jsp"><c:param name="title" value="Happy Heart"/></c:import>


  <!-- *****************************************************************************************************************
   BLUE WRAP
   ***************************************************************************************************************** -->
  <div id="blue">
    <div class="container">
      <div class="row">
        <h3>Create an Account.</h3>
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
        <form class="form white-text" action="<%= request.getContextPath()%>/login?action=registerin" method="POST">
				
				<p>${message}</p>
        <p>${email}</p>
				
				<div class="form-group">
					<label for="femail">FULL NAME:</label> 
					<input type="text" name="name"
						class="form-control" required="required" placeholder="Enter your full name"
						data-rule="minlen:4" data-msg="Please enter at least 4 chars">
					<div class="validate"></div>
				</div>

				<div class="form-group">
             <label for="femail">EMAIL:</label>
            <input type="email" name="email" class="form-control" required="required" placeholder="Your Email" data-rule="email" data-msg="Please enter a valid email">
            <div class="validate"></div>
            </div>
            
 				<div class="form-group">
             <label for="phone">PHONE:</label>
            <input type="tel" name="phone" class="form-control" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required="required" placeholder="000-000-0000" data-rule="tel" data-msg="Please enter a valid phone">
            <div class="validate"></div>
            </div>           
            
          <div class="form-group">
            <label for="fpassword">PASSWORD:</label>
            <input type="password" name="password" class="form-control"  required="required" placeholder="Password" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject">
            <div class="validate"></div>
          </div>
  
          <div class="form-group">
            <label for="fnoregistered">Already registered?</label>
            <a href="<%= request.getContextPath()%>/login.jsp" style="form-control">Log in ></a>          
          </div> 

          <div class="loading"></div>
          <div class="error-message"></div>
            
          <div class="form-send">
            <input type="hidden" name="action" value="registerin">
            <button type="submit" class="btn btn-theme btn-white mt30">Create an Account</button>
          </div>
          
          <div class="form-group">
            <br/>
            <a href="<%= request.getContextPath()%>/index.jsp" style="form-control">Cancel ></a>          
          </div> 
          
        </form>			
			
      <!-- /Register Form -->
		</div>
	</div>
</div>

<c:import url="assets/include/footer.jsp"></c:import>