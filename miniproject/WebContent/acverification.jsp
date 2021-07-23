<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>email verification page</title>
</head>
<body>
<%
String acno=(String)request.getAttribute("acno");

try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	PreparedStatement ps = conn.prepareStatement("UPDATE userdata SET status=? WHERE acno =? ");
	ps.setString(1,"active");
	ps.setString(2,acno);
	int count = ps.executeUpdate();
	ps.close();
}catch(Exception e1) { System.out.println(e1);}

try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	PreparedStatement st = conn.prepareStatement("CREATE TABLE "+acno+"  (srno INTEGER auto_increment,amount INTEGER,BeneficlaryName VARCHAR(255),acno VARCHAR(255),time Time,date Date,PRIMARY KEY (srno));");
	int count = st.executeUpdate();
	st.close();
	response.sendRedirect("home.jsp");
	}catch(Exception e1) { System.out.println(e1);}
%>
</body>
</html>