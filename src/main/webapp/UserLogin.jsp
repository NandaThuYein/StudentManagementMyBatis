<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="./stylesheets/test.css" rel="stylesheet" />
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${error}</p>
            <h5 style="color:darkblue;">${succ}</h5>
          </div>
        </div>
        <form class="login-form" action="UserLoginServlet" method="post" name="confirm">
          <input type="text" placeholder="User Name" name="userName" value="${bean.userName}" />
          <input type="password" placeholder="Password" name="userPassword" value="${bean.userPassword}" />
          <button type="submit">login</button>
          <p class="message">Not registered? <a href="CreateUser.jsp">Create an account</a></p>
        </form>
      </div>
    </div>
</body>
</html>