<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url="assets/include/header.jsp"><c:param name="title" value="Happy Heart"/></c:import>

  <!-- *****************************************************************************************************************
   BLUE WRAP
   ***************************************************************************************************************** -->
  <div id="blue">
    <div class="container">
      <div class="row">
        <h3>Enter Your Password</h3>
      </div>
      <!-- /row -->
    </div>
    <!-- /container -->
  </div>
  <!-- /blue -->


  <!-- *****************************************************************************************************************
   CONTACT FORMS
   ***************************************************************************************************************** -->

  <div class="container mtb">
    <div class="row">
      <div class="col-lg-8">
        <h4>Enter Your Password</h4>
        <div class="hline"></div>
        <div class="col-md-4 col-md-offset-4">
         <br/>      
        <br/> 
        <form class="form white-text" action="<%= request.getContextPath()%>/login?action=password" method="POST">

          <div class="form-group">
          
            <p>${message}</p>
            <p>${email}</p>
            
            <label for="fpassword">Password:</label>
            <input type="password" name="password" class="form-control"  required="required" placeholder="Password" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject">
            <div class="validate"></div>
          </div>
          <div class="form-group">
            <input type="checkbox" name="rememberme" title="Remember me" placeholder="Remember me"> <label for="frememberme">Remember me</label> 
            <div class="validate"></div>
          </div>  
          <div class="form-group">
            <a href="#" style="form-control">Forgot password?</a>          
          </div>  
          <div class="form-group">
            <label for="fpassword">Not registered yet?</label>
            <a href="#" style="form-control">Create a Happy Heart account ></a>          
          </div> 

          <div class="loading"></div>
          <div class="error-message"></div>
            
          <div class="form-send">
            <input type="hidden" name="action" value="password">
            <button type="submit" class="btn btn-theme btn-white mt30">Sign In</button>
          </div>
          
        </form>
        </div>
        <br/>
          
      </div>
  </div>
</div>

<c:import url="assets/include/footer.jsp"></c:import>