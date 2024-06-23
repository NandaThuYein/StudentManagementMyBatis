<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*" %>
    <%@ page import="java.util.Arrays" %>
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
        
        <!-- Data Table css1 js1 -->
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Student Search Page</title>
        <style>
        	#sub_content{
        		padding-right:200px;
        	}
        </style>
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
      <div id="sub_content">
      <form class="row g-3 mt-3 ms-2">
      </form>
	<div class="container">
		<div class="row">
				<div class="col-md-10">
      <table class="table border" id="studentTable">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Details</th>
          </tr>
        </thead>
        <tbody>
        <% int count = 1; %>
        <c:forEach var="data" items="${requestScope.studentList}">
	          <tr>
	            <th scope="row"><%= count++ %></th>
	            <td>${data.studentId}</td>
	            <td>${data.studentName}</td>
	            <td>
	            <c:forEach var="sc" items="${requestScope.scList}">
	            	<c:if test="${data.studentId eq sc.studentId}">
	            		${sc.courseName} <br/>
	            	</c:if>
	            </c:forEach>
	            </td>
	            <td>
	              <a href="UpdateStudentServlet?studentId=${data.studentId}"><button type="submit" class="btn btn-secondary mb-2">See More</button></a> 
	            </td>
	          </tr>
          </c:forEach>
          <tr>
          		<td colspan="5"><span style="color:red;">${noStudent}</span></td>
          </tr>
        </tbody>
      </table>
      	</div>
      </div>
      </div>
    </div>
    </div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <!-- JQuery js1 -->
		<script src="https://code.jquery.com/jquery-3.6.1.min.js" type="text/javascript"></script>
		<!-- Data Table css1 js1 -->
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script>
		        $(document).ready(function(){
					$('#studentTable').DataTable();
				});
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