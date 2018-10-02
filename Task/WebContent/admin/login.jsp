<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ISAT</title>       
  <style>
  <%@include file ="/templates/admin/CSS/style.css"%>
  </style>
</head>
<body>

  <div class="login-form">
    <form action = "<%=request.getContextPath() %>/admin/login" method = "POST">
      <span>Login</span>
      <input type="email" placeholder="Your e-mail" required name="username"/>
      <input type="password" placeholder="Your password" required name = "password"/>
      <input type="submit" value="Login"/>
    </form>
    <a href="#">Forgot password</a>
    <a href="#" style="float: right;">Sign up here</a>
  </div>
</body>