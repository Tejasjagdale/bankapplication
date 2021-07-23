package miniproject;

import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;

public class logindao{
	HttpServletRequest request;
	HttpServletResponse response;
	String database;
	
	public boolean check(String ui,String pass)
	{
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			PreparedStatement st = conn.prepareStatement("SELECT*from  userdata where userid=? && password=?");
			st.setString(1,ui);
			st.setString(2,pass);
			ResultSet rs =  st.executeQuery();

			if(rs.next())
			{
				return true;
			}
			st.close();
			}catch(Exception e1) { System.out.println(e1);}
		
		return false;
	}
	
	public boolean signin(String password,String acno,String cpassword)
	{
       if(password.equals(cpassword))
       {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			PreparedStatement ps = conn.prepareStatement("select*from userdata WHERE acno=? ");
			ps.setString(1,acno);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
			    return true;
			}
		    }catch(Exception e1) { System.out.println(e1);}
       }
       
       
		return false;
	}
	
	public boolean checke(String acno)
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/phpbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			PreparedStatement st = conn.prepareStatement("SELECT*FROM userdata where acno=? ");
			st.setString(1,acno);
			ResultSet rs =  st.executeQuery();
			if(rs.next())
			{
		    System.out.print("working");
			return true;
			}
			st.close();
			}catch(Exception e1) { System.out.println(e1);}
		
		return false;
		
	}
	
	
}
