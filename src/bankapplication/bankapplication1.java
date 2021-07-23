package bankapplication;

import java.util.Scanner;

import java.sql.*;
public class bankapplication1{
public static void main(String[] args){

String[] acno={"AC00101","AC00102","AC00103"};
int[] password = {5019123,5019115,5019104};

System.out.println("");
System.out.println("---------------------------------------------------------------");
System.out.println("Enter  your account no:");
System.out.println("---------------------------------------------------------------");
Scanner input1= new Scanner(System.in);
String acnoc=input1.next();

System.out.println("---------------------------------------------------------------");
System.out.println("Enter  your password:");
System.out.println("---------------------------------------------------------------");
Scanner input2= new Scanner(System.in);
int passwordc=input2.nextInt();

try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
PreparedStatement st = conn.prepareStatement("SELECT balance  FROM userdata where acno=? && pin=?");
st.setString(1,acnoc);
st.setInt(2,passwordc);
ResultSet rs =  st.executeQuery();
rs.next();
int pbalance =rs.getInt("balance");

st.close();

for(int i=0;i<=2;i++)
{
if(acno[i].equals(acnoc) && password[i] == passwordc)
{
BankAccount obj1 = new BankAccount(pbalance,acnoc);
obj1.showMenu();
}
}
}catch(Exception e1) { System.out.println(e1);}
}
}


class BankAccount{

int balance;
int prevtran;
String ACNO;
String method;
int svbalance;

BankAccount(int a,String b)
{
	balance = a;
	ACNO = b;
}

void deposit(int amt)
{

if(amt!=0 && amt<=50000)
{
balance=balance+amt;
prevtran=amt;
System.out.println("");
System.out.println("amount deposited sucessfully");
}

if(amt>50000)
{
System.out.println("");
System.out.println("plese try with less amount");
}
}

void withdraw(int amt)
{
if(amt!=0 && amt<=balance && amt<=25000)
{
balance=balance-amt;
prevtran=-amt;
System.out.println("");
System.out.println("amount withdrawn sucessfully");
}

else
{
System.out.println("");
System.out.println("Transaction failed :Amount cannot be withdrawned ");
}
}

void getPrevTransac()
{
if(prevtran>0)
{
System.out.println("Deposited : "+ prevtran); 
}

else if (prevtran<0)
{
System.out.println("Withdrawn : "+ Math.abs(prevtran));
}

else
{
System.out.println("No transaction occured");
}
}

void amttransfer(String ACNO)
{
	
	String[] acno={"AC00101","AC00102","AC00103"};
    int tobalance;
    
    System.out.println("");
	System.out.println("---------------------------------------------------------------");
	System.out.println("Enter amount to be transfer:");
	System.out.println("---------------------------------------------------------------");
	Scanner input3= new Scanner(System.in);
	int amtt=input3.nextInt();
	
		for(int i=0;i<=2;i++)
		{
		if(acno[i].equals(ACNO))
		{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				PreparedStatement st = conn.prepareStatement("SELECT balance  FROM userdata where acno=?");
				st.setString(1,ACNO);
				ResultSet rs =  st.executeQuery();
				rs.next();
				int pbalance =rs.getInt("balance");
				svbalance=pbalance;
				
				st.close();
				}catch(Exception e1) { System.out.println(e1);}
			
			if(balance>=amtt)
			{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				PreparedStatement ps = conn.prepareStatement("UPDATE userdata SET balance=? WHERE acno =? ");
				tobalance=svbalance+amtt;
				ps.setInt(1,tobalance);
				ps.setString(2,ACNO);
				int count = ps.executeUpdate();
				ps.close();
				balance=balance-amtt;
				System.out.println("");
				System.out.println("amount transfer sucessfully");
			}catch(Exception e1) { System.out.println(e1);}
	    }
		}
	}	
	}

void showMenu()
{
char opt = '\0';
char opt1 = '\0';
Scanner obj = new Scanner(System.in);
Scanner obj2 = new Scanner(System.in);

System.out.println("\n");
System.out.println("Wellcome to our Banking service");
System.out.println("\n");
System.out.println("A. Check Balance");
System.out.println("B. Deposit");
System.out.println("C. Withdraw");
System.out.println("D. Previous Transaction");
System.out.println("T. Amount Transfer");
System.out.println("F. Calculator");
System.out.println("E. Exit");

do
{
System.out.println("------------------------------------------------------------------");
System.out.println("Enter your option");
System.out.println("------------------------------------------------------------------");
opt = obj.next().charAt(0);
System.out.println("\n");

switch(opt)
{
case 'A':
System.out.println("--------------------------------------------------------------");
System.out.println("Balance ="+balance);
System.out.println("--------------------------------------------------------------");
break;

case 'B':
System.out.println("-------------------------------------------------------------");
System.out.println("Enter the amount to be deposited :");
System.out.println("--------------------------------------------------------------");
int amt = obj.nextInt();
deposit(amt);
break;

case 'C':
System.out.println("--------------------------------------------------------------");
System.out.println("Enter the amount to be withdrawn :");
System.out.println("--------------------------------------------------------------");
int amt2 = obj.nextInt();
withdraw(amt2);
break;

case 'D':
System.out.println("---------------------------------------------------------------");
getPrevTransac();
System.out.println("---------------------------------------------------------------");
System.out.println("");
break;

case 'F':
System.out.println(" ");
System.out.println("A.Normal calculator");
System.out.println("B.EMI calculator");
System.out.println("C.FD calculator");
System.out.println("D.SIP calculator");
System.out.println("Z.back");

do
{
System.out.println("---------------------------------------------------------------");
System.out.println("Enter your option");
System.out.println("------------------------------------------------------------------");
opt1 = obj2.next().charAt(0);

switch(opt1)
{
case 'A':
System.out.println("\n");
System.out.println("--------------------------------------------------------------");
System.out.println("This is under devlopment");
System.out.println("--------------------------------------------------------------");
break;

case 'B':
System.out.println("\n");
System.out.println("-------------------------------------------------------------");
System.out.println("this is under devlopment");
System.out.println("--------------------------------------------------------------");
break;

case 'C':
System.out.println("\n");
System.out.println("--------------------------------------------------------------");
System.out.println("This is under devlopment");
System.out.println("--------------------------------------------------------------");
break;

case 'D':
System.out.println("\n");
System.out.println("-------------------------------------------------------------");
System.out.println("this is under devlopment");
System.out.println("--------------------------------------------------------------");
break;

case 'Z':
System.out.println("\n");
System.out.println("Wellcome to our Banking service");
System.out.println("\n");
System.out.println("A. Check Balance");
System.out.println("B. Deposit");
System.out.println("C. Withdraw");
System.out.println("D. Previous Transaction");
System.out.println("L. Loan");
System.out.println("F. Calculator");
System.out.println("E. Exit");
break;
}
}while(opt1!='Z');
break;

case 'T':
System.out.println("---------------------------------------------------------------");
System.out.println("Enter Account no:");  
System.out.println("---------------------------------------------------------------");
String Acno = obj.next();
amttransfer(Acno);
break;
	
case 'E':
System.out.println("---------------------------------------------------------------");
System.out.println("Thank you for using our services");  
System.out.println("---------------------------------------------------------------");
break;
	          
}

}while(opt!='E');

obj2.close();
obj.close();
try {
	if(prevtran>0)
	{
	    method = "Deposited";		
	}
	else if (prevtran<0)
	{
		method = "Withdraw";
		prevtran=-prevtran;
	}
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	PreparedStatement ps = conn.prepareStatement("UPDATE userdata SET balance=? , prevtranc=? WHERE acno =? ");
	ps.setInt(1,balance);
	ps.setString(2,method +" rs"+ prevtran);
	ps.setString(3,ACNO);

	int count = ps.executeUpdate();
	System.out.println(count + " rows affected");
	ps.close();
    }catch(Exception e1) { System.out.println(e1);}

}
}

