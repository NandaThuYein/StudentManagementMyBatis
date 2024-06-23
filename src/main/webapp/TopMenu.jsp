<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.io.*,java.util.*" %>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="./stylesheets/test.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<title>Top Menu MNU001</title>
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
				<div class="col-md-1">
					<input type="button" class="btn-basic" id="lgnout-button"
						value="Log Out" onclick="location.href='UserLoginServlet'">
				</div>
			</div>
		</div>

	</div>
	<!-- <div id="testsidebar">Hello World </div> -->
	<div class="sidenav">

		<button class="dropdown-btn">
			Class Management <i class="fa fa-caret-down"></i>
		</button>

		<div class="dropdown-container">
			<a href="CourseRegistration.jsp">Course Registration</a> 
			<a href="AddStudentServlet">Student Registration</a> 
			<a href="SearchStudentServlet">Student Search </a>
		</div>
		<a href="UserRegistration.jsp">Users Management</a>
	</div>
	<div class="main-contents">
		<div id="contents">
			<h3>
				Welcome Back...! <br>
				<br> Enjoy this project and try your best in your own!
			</h3>
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