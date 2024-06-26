<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*" %>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./stylesheets/test.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Update User Page</title>
</head>

<body>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <h3><a href="TopMenu.jsp">Student Registration</a></h3>
        </div>  
        <div class="col-md-6">
            <p>User: ${sessionScope.res.userId} ${sessionScope.res.userName}</p>
            <p>Current Date : 
            			<% 
				                    String am_pm;
				                    Calendar calendar = new GregorianCalendar();
				                    int hour = calendar.get(Calendar.HOUR);
				                    int minute = calendar.get(Calendar.MINUTE);
				                    int second = calendar.get(Calendar.SECOND);
				                    if(calendar.get(Calendar.AM_PM ) == 0) {
				                    	am_pm = "AM";
				                    }else {
				                    	am_pm = "PM";
				                    }
				                    String CT = hour+":"+minute+":"+second+"  "+am_pm;
				                    out.println(CT);
                    	%>
            </p>
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick="location.href='UserLoginServlet'">
        </div>        
    </div>
</div>

</div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
            <div class="dropdown-container">
          <a href="CourseRegistration.jsp">Course Registration </a>
          <a href="AddStudentServlet">Student Registration </a>
          <a href="SearchStudentServlet">Student Search </a>
        </div>
        <a href="SearchUser.jsp">Users Management</a>
      </div>
      <div class="main_contents">
    <div id="sub_content">
        <form action="UpdateUserServlet" method="post">

        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Update</h2>
        <div class="row mb-2">
			<div class="col-md-2"></div>
			<label for="email" class="col-md-2 col-form-label"></label>
			<div class="col-md-4">
				<h5 style="color:red;">${error}</h5>
				<h5 style="color:darkblue">${msg}</h5>
			</div>
		</div>
        <div class="row mb-4">
			<div class="col-md-2"></div>
			<label for="email" class="col-md-2 col-form-label">ID</label>
			<div class="col-md-4">
					<input type="text" name="userId" class="form-control" id="email" value="${bean.userId}" readonly="readonly" />
			</div>
		</div>
		<div class="row mb-4">
			<div class="col-md-2"></div>
			<label for="email" class="col-md-2 col-form-label">Name</label>
			<div class="col-md-4">
				<input type="text" name="userName" class="form-control" id="email" value="${bean.userName}" />
			</div>
		</div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="email" class="col-md-2 col-form-label">Email</label>
            <div class="col-md-4">
                <input type="email" name="userEmail" class="form-control" id="email" value="${bean.userEmail}" />
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="Passowrd" class="col-md-2 col-form-label">Password</label>
            <div class="col-md-4">
                <input type="password" name="userPassword" class="form-control" id="name" value="${bean.userPassword}" />
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="confirmPassword" class="col-md-2 col-form-label">Confirm Password</label>
            <div class="col-md-4">
                <input type="password" name="confPassword" class="form-control" id="confirmPassword" value="${bean.confPassword}" />
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="userRole" class="col-md-2 col-form-label">User Role</label>
            <div class="col-md-4">
                <select name="userRole" class="form-select" aria-label="Education" id="userRole">
                    <c:if test="${bean.userRole == 'Admin'}">
                    	<option selected>${bean.userRole}</option>
                    	<option value="User">User</option>
                    </c:if>
                    <c:if test="${bean.userRole == 'User'}">
                    	<option selected>${bean.userRole}</option>
                    	<option value="Admin">Admin</option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-4"></div>
            <div class="col-md-6">
                <button type="submit" class="btn btn-success col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Update</button>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">User Update</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                               
                               <h5 style="color: rgb(127, 209, 131);">Successfully Updated !</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
                               
                            </div>
                        </div>
                    </div>
            </div>
            <button type="button" class="btn btn-secondary col-md-2 " onclick="location.href = 'SearchUser.jsp';">
                Back
            </button>
        </div>
        </div>
        </form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</body>
</html>