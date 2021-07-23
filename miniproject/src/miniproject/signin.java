package miniproject;

import java.io.IOException;

import java.sql.*;
import java.util.Properties;
import java.io.PrintWriter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signin")
public class signin extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		String password= request.getParameter("password");
		String cpassword= request.getParameter("cpassword");
		String email=request.getParameter("email");
		String userid= request.getParameter("userid");
		String acno=request.getParameter("ACNO");
		int temp=0;
		
		logindao dao= new logindao();
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		PreparedStatement st2 = conn1.prepareStatement("SELECT `userid`,`email` from userdata");
		ResultSet rs2=  st2.executeQuery();
		

		if(dao.signin(password,acno,cpassword))
		{
			while(rs2.next())
			{
				if(userid.equals(rs2.getString("userid")))
				{
				temp=1;	
				}
				if(email.equals(rs2.getString("email")))
				{
				temp=2;	
				}
			}
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			PreparedStatement st1 = conn2.prepareStatement("SELECT `userid` from userdata where acno=?");
			st1.setString(1,acno);
			ResultSet rs =  st1.executeQuery();
			
			rs.next();
			String pcuid=rs.getString("userid");
			
	        if(pcuid.equals("notreg"))
			{
				if(temp==0)
			   {
				mail sendmail=new mail();
				if(sendmail.otpmail(email)) {
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
						PreparedStatement st = conn.prepareStatement("UPDATE `userdata` SET `email`=?,`userid`=?,`password`=?,`username`=?,`status`=? WHERE acno=?");
						st.setString(1,email);
						st.setString(2,userid);
						st.setString(3,password);
						st.setString(4,name);
						st.setString(5,"inactive");
						st.setString(6,acno);
						int count = st.executeUpdate();
						st.close();
						response.sendRedirect("home.jsp");
						}catch(Exception e1) { System.out.println(e1);}
				}
				}else
				{
					if(temp==1)
					{
						response.setContentType("text/html");
						PrintWriter pw=response.getWriter();
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('these userid is already been taken');");
						pw.println("</script>");
						RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
						rd.include(request, response);
					}
					if(temp==2)
					{
						response.setContentType("text/html");
						PrintWriter pw=response.getWriter();
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('these email id is already register');");
						pw.println("</script>");
						RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
						rd.include(request, response);
					}
				}
			}
	        else
			{
	        	System.out.print(pcuid);
				response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('these account number has already register');");
				pw.println("</script>");
				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
				rd.include(request, response);
			}
				
		}
		else
		{
		 PrintWriter out =response.getWriter();
		 if(password.equals(cpassword))
	     {
		 response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('your account number was not found');");
			pw.println("</script>");
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
	     }
		 else
		 {
			 response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('sorry password are not matching');");
				pw.println("</script>");
				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
				rd.include(request, response);
				
		 }
		}
		
		st2.close();
		}catch(Exception e1) { System.out.println(e1);}
		
		
	}
}
