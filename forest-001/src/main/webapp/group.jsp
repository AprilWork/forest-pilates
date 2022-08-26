<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

  <!-- *****************************************************************************************************************
   CLASS
   ***************************************************************************************************************** -->

      <div class="col-lg-3 col-md-3 col-sm-3">
        <div class="he-wrap tpl6">
          <img src="${param.path}${param.nameImageFile}" alt="">
          <div class="he-view">
            <div class="bg a0" data-animate="fadeIn">
              <h3 class="a1" data-animate="fadeInDown">Contact Me:</h3>
              <a href="#" class="dmbutton a2" data-animate="fadeInUp"><i class="fa fa-envelope"></i></a>
              <a href="#" class="dmbutton a2" data-animate="fadeInUp"><i class="fa fa-twitter"></i></a>
            </div>
            <!-- he bg -->
          </div>
          <!-- he view -->
        </div>
        <!-- he wrap -->
        <h4>${param.name}</h4>
        <h5 class="ctitle"></h5>
        <p>${param.description}</p>
        <div class="hline"></div>
      </div>

