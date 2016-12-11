<%@page import="controller.FBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	FBConnection fbConnection = new FBConnection();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java Facebook Login</title>
</head>
<body style="text-align: center; margin: 0 auto;">
	<div
		style="margin: initial; background-image: url(./img/fbloginbckgrnd.jpg); height: 200px; width: 400px;">
		<a href="<%=fbConnection.getFBAuthUrl()%>"> <img
			style="margin-top: 100px;" src="./img/facebookloginbutton.png" />
		</a>
	</div>
</body>
</html>
