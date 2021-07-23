package miniproject;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class login extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userid= request.getParameter("tif");
		String password= request.getParameter("nif");
		
		logindao dao= new logindao();
		
		if(dao.check(userid,password))
		{
			
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				PreparedStatement st = conn.prepareStatement("SELECT balance,username,acno,email,status  FROM userdata where userid=? && password=?");
				st.setString(1,userid);
				st.setString(2,password);
				ResultSet rs =  st.executeQuery();
				rs.next();
				String username1 =rs.getString("username");
				String ACNO =rs.getString("acno");
				String email =rs.getString("email");
				String status =rs.getString("status");
				int cbalance =rs.getInt("balance");
				
				session.setAttribute("userid",userid);
				session.setAttribute("Email",email);
				session.setAttribute("name",username1);
				session.setAttribute("bal",cbalance);
				session.setAttribute("acno",ACNO);
				
		    if(status.equals("active"))
		    {
				response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('login successfull');");
				pw.println("</script>");
				response.sendRedirect("login.jsp");
		    }else
		    {
		    	response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('your account is not active please check your email');");
				pw.println("</script>");
		    }
				st.close();
		        }catch(Exception e1) { System.out.println(e1);}
			
			
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Invalid Username or Password');");
			pw.println("</script>");
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
		}
	}

}
