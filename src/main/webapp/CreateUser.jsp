<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./stylesheets/test.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<title> Create User Account </title>
</head>
<body class="login-page-body"> 
    <div class="container-fluid">
            <form action="CreateUserServlet" method="post">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Create User Account</h2>
                <div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label"></label>
						<div class="col-md-4">
							<h5 style="color:red;">${error}</h5>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">ID</label>
						<div class="col-md-4">
							<input type="text" name="userId" class="form-control" id="email" value="${bean.userId}" placeholder="Enter your Id" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" name="userName" class="form-control" id="email" value="${bean.userName}" placeholder="Enter your name" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">Email</label>
						<div class="col-md-4">
							<input type="email" name="userEmail" class="form-control" id="email" value="${bean.userEmail}" placeholder="Enter your email" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="Passowrd" class="col-md-2 col-form-label">Password</label>
						<div class="col-md-4">
							<input type="password" name="userPassword" class="form-control" id="name" value="${bean.userPassword}" placeholder="Enter your password" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="confirmPassword" class="col-md-2 col-form-label">Confirm Password</label>
						<div class="col-md-4">
							<input type="password" name="confPassword" class="form-control" id="confirmPassword" value="${bean.confPassword}" placeholder="Enter your confirm password" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
							<label for="userRole" class="col-md-2 col-form-label">User Role</label>
						<div class="col-md-4">
							<select name="userRole" class="form-select" aria-label="Education" id="userRole">
								<option selected>Admin</option>
								<option value="User" ${bean.userRole == 'User'?'selected':''}>User</option>
							</select>
						</div>
					</div>
            <div class="row mb-4">
                <div class="col-md-4"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-secondary col-md-2">Create</button>
                    <a href="UserLogin.jsp"><button type="button" class="btn btn-primary col-md-2">Login</button></a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>