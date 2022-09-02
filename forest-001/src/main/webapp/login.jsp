<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url="assets/include/header.jsp"><c:param name="title" value="Happy Heart"/></c:import>

  <!-- *****************************************************************************************************************
   BLUE WRAP
   ***************************************************************************************************************** -->
  <div id="blue">
    <div class="container">
      <div class="row">
        <h3>SIGN IN.</h3>
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
        <h4>First, enter your email address.</h4>
        <div class="hline"></div>
        <div class="col-md-4 col-md-offset-4">
         <br/> 
        <div class="row">
        
        <form class="form white-text" action="<%= request.getContextPath()%>/login?action=signin" method="get">
          <div class="form-send">
            <input type="hidden" name="action" value="sign_with_google">
            <button type="submit" name="google" class="btn btn-theme btn-white mt30"  >Sign in with Google</button>
          </div>          
        </form>
        </div>
        
        <div class="row">
         <form class="form white-text" action="<%= request.getContextPath()%>/login" method="get">
          <div class="form-send">
            <input type="hidden" name="action" value="sign_with_facebook">
            <button type="submit" name="facebook" class="btn btn-theme btn-white mt30">Sign in with Facebook</button>
          </div>  
        </form> 
        </div>      
        <br/> 
        
        <form class="form white-text" action="<%= request.getContextPath()%>/login?action=signin" method="POST">

          <p>${message}</p>
               
          <div class="form-group">
             <label for="femail">Email:</label>
            <input type="email" name="email" class="form-control" required="required" placeholder="Your Email" data-rule="email" data-msg="Please enter a valid email">
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
            <a href="register.jsp" style="form-control">Create a Happy Heart account ></a>          
          </div> 
            
          <div class="form-send">
            <button type="submit" class="btn btn-theme btn-white mt30">Continue</button>
          </div>
          
        </form>
        </div>
        <br/>
          
      </div>
  </div>
</div>

<c:import url="assets/include/footer.jsp"></c:import>