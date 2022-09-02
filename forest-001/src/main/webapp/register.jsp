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
				
				<!--  <form class="contact-form php-mail-form" role="form" action="<%= request.getContextPath()%>/login?action=registerin" method="POST"> -->
				
				<p>${message}</p>
         
				<div class="form-group">
             <label for="email">EMAIL:</label>
            <input type="email" name="email" class="form-control" 
            required="required" placeholder="Your Email">
            <div class="validate"></div>
            </div>
            
 				<div class="form-group">
             <label for="phone">PHONE:</label>
            <input type="tel" name="phone" class="form-control" 
            pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" 
            required="required" placeholder="000-000-0000">
            <div class="validate"></div>
            </div>           
            
          <div class="form-group">
            <label for="password">CHOOSE PASSWORD:</label>
            <input type="password" name="password" class="form-control"  
            required="required" placeholder="Password"
            pattern=".{8,}">
            <div class="validate"></div>
          </div>
          
          <div class="form-group">
            <label for="retypepassword">RE-TYPE PASSWORD:</label>
            <input type="password" name="retypepassword" class="form-control"  
            required="required" placeholder="Please repeat password"
            pattern=".{8,}">
            <div class="validate"></div>
          </div>        
  
          <div class="form-group">
            <label for="noregistered">Already registered?</label>
            <a href="<%= request.getContextPath()%>/login.jsp" style="form-control">Log in ></a>          
          </div> 

          <div class="loading"></div>
          <div class="error-message"></div>
          <div class="sent-message">Your account has been registered. Thank you!</div>
            
          <div class="form-send">
            <!--  <input type="hidden" name="action" value="registerin">-->
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
