<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url="assets/include/header.jsp"><c:param name="title" value="Happy Heart"/></c:import>


  <!-- *****************************************************************************************************************
   BLUE WRAP
   ***************************************************************************************************************** -->
  <div id="blue">
    <div class="container">
      <div class="row">
        <h3>Addresses.</h3>
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
        <form class="form white-text" action="<%= request.getContextPath()%>/login?action=addresses" method="POST">
				
				<!--  <form class="contact-form php-mail-form" role="form" action="<%= request.getContextPath()%>/login?action=registerinfo" method="POST"> -->
				
				<p>${message}</p>

				<div class="form-group">
          <label for="street">STREET:</label> 
          <input type="text" name="street"
            value="${street}"
            class="form-control" required="required" 
            placeholder="Enter your street">
          <div class="validate"></div>
        </div>
        
        <div class="form-group">
          <label for="city">CITY:</label> 
          <input type="text" name="city"
            value="${city}"
            class="form-control" required="required" 
            placeholder="Enter your city">
          <div class="validate"></div>
        </div>
        
        <div class="form-group">
          <label for="zipcode">ZIP CODE:</label> 
          <input type="text" name="zipcode"
            value="${zipcode}"
            class="form-control" required="required"
            pattern="[0-9]{5}" 
            placeholder="Enter your zip code">
          <div class="validate"></div>
        </div>
        
         <div class="form-group">
          <label for="sameaddress">Billing address the same as home address:</label> 
          <input type="checkbox" name="sameaddress"
            class="form-control" required="required" 
            checked="checked">
          <div class="validate"></div>
        </div>                       
            
          <div class="form-send">
            <button type="submit" class="btn btn-theme btn-white mt30">SAVE ADRESSES</button>
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
