package miniproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/transaction")
public class transaction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		String raccount= request.getParameter("acno");
		String BeneficlaryName= request.getParameter("bn");
		int amount= Integer.parseInt(request.getParameter("att"));
		DateTimeFormatter time=DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter date=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now=LocalDateTime.now();
		String database;
		
		Integer ubalance= (int) session.getAttribute("bal");
		String userid=session.getAttribute("userid").toString();
		String uacno=session.getAttribute("acno").toString();
		int tobalance;
		int pbalance;
		
         logindao dao= new logindao();
		
		if(dao.checke(raccount))
		{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				PreparedStatement st = conn.prepareStatement("SELECT balance  FROM userdata where acno=?");
				st.setString(1,raccount);
				ResultSet rs =  st.executeQuery();
				rs.next();
				pbalance =rs.getInt("balance");
				
			
			if(amount<=50000)
			{
			 if(ubalance>=amount)
			 {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost/phpbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				PreparedStatement ps = conn2.prepareStatement("UPDATE userdata SET balance=? WHERE acno =? ");
				tobalance=pbalance+amount;
				ps.setInt(1,tobalance);
				ps.setString(2,raccount);
				int count = ps.executeUpdate();
				
				ubalance=ubalance-amount;
				session.removeAttribute("bal");
				session.setAttribute("bal",ubalance);
				
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
					PreparedStatement ps1 = conn3.prepareStatement("UPDATE userdata SET balance=? WHERE  userid=? ");
					ps1.setInt(1,ubalance);
					ps1.setString(2,userid);

					int count3 = ps1.executeUpdate();
					
					 response.setContentType("text/html");
						PrintWriter pw=response.getWriter();
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('transaction successfully');");
						pw.println("window.location='login.jsp';");
						pw.println("</script>");
						
		 
	
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connt = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				PreparedStatement ist = connt.prepareStatement("INSERT INTO "+uacno+" (`amount`, `BeneficlaryName`, `acno`, `time`, `date`) VALUES (?,?,?,?,?)");
				ist.setInt(1,amount);
				ist.setString(2,BeneficlaryName);
				ist.setString(3,raccount);
				ist.setString(4,time.format(now));
				ist.setString(5,date.format(now));
				ist.execute();
			 }
			 else
			 {
				 response.setContentType("text/html");
					PrintWriter pw=response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('you don't have enough balance');");
					pw.println("</script>");
					RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
					rd.include(request, response);
			 }
			}
			else
			{
				 response.setContentType("text/html");
					PrintWriter pw=response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('sorry we have daily limit of 25000 try with less amount');");
					pw.println("</script>");
					RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
					rd.include(request, response);
				 
			}
			
			st.close();
			}catch(Exception e1) { System.out.println(e1);}
	}
		else
		{
			 response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('please enter valid Account number');");
				pw.println("</script>");
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
				
		}
	
		}
	}
