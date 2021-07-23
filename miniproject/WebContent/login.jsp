<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*,java.io.*,java.util.*" %>

<%
response.setHeader("cache-Control","no-cache,no-store,must-revalidate");

response.setHeader("pragma","no-cache");

response.setHeader("Expires","0");

if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>
bank login page
</title>
<link rel="stylesheet" type="text/css" href="login.css">
</head>

<body>

<span class="icon" onclick="menu()">></span>
<div class="slide">
<ul class="menu">
<li class="selected">online banking</li>
<li class="li" onclick="showtfh()"><p class="hcc">display transactions</p></li>
<li class="li" onclick="alert2()"><p class="hcc">amount balance?</p></li>
<li class="li" onclick="showtf()" ><p class="hcc" >fund transfer</p></li>
<li class="li" ><p class="hcc">calculators</p></li>
<li class="li" ><p class="hcc">menu 5</p></li>
<li class="li" ><p class="hcc">menu 6</p></li>
</ul>
</div>
<span class="return" onclick="back()"><</span>


<div class="amenuc">
<div id="rb1"></div>
<div id="rb2"></div>
<div id="rb3"></div>
<div class="mcard1"></div>
<div class="mcard2"></div>
<div class="mcard3"></div>
</div>
<div class="cmenu">
<button class="mbtn1" onclick="showabc1()">button1</button>
<button class="mbtn2" onclick="showabc2()">button2</button>
<button class="mbtn3" onclick="showabc3()">button3</button>
</div>

<ul id="hmenu1">
<li><p class="hmt">option1</p></li>
<li><p class="hmt">option2</p></li>
<li><p class="hmt">option3</p></li>
<li><p class="hmt">option4</p></li>
<li><p class="hmt">option5</p></li>
</ul>

<ul id="hmenu2">
<li><p class="hmt1" id="am1" onclick="stoptf()"></p></li>
<li><p class="hmt1" id="am2"></p></li>
<li><p class="hmt1" id="am3"></p></li>
</ul>

<div class="alert2" onclick="stop()">
<div class="ialert2">
<%
try
{
out.print(session.getAttribute("name").toString());
}catch(Exception e1) { System.out.println(e1);}
%>
your balance is : <p class="amt">
<%
try
{
out.print((int)session.getAttribute("bal"));
}catch(Exception e1) { System.out.println(e1);}
%>Rs
</p>
</div>
</div>

<header>

<div class="hd">
Bank application
</div>
<div class="hd1">
</div>
<div class="hd2">
</div>

<form action="logout" method="post">
<input type="submit" value="logout" class="hlb">
</form>

</header>

<main>

<div class="dftt">
<table id="table">

<thead>
<tr>
<th>srno</th>
<th>Amount</th>
<th>Beneficlary name</th>
<th>Account no</th>
<th>time</th>
<th>Date</th>
</tr>
</thead>

<%
try{
	String uacno=session.getAttribute("acno").toString();
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	PreparedStatement st = conn.prepareStatement("SELECT*FROM "+uacno+" ");
	ResultSet rs =  st.executeQuery();
while(rs.next())
{
%>
<tr>
<td><%out.print(rs.getInt("srno"));%></td>
<td><%out.print(rs.getInt("amount"));%></td>
<td><%out.print(rs.getString("BeneficlaryName"));%></td>
<td><%out.print(rs.getString("acno"));%></td>
<td><%out.print(rs.getString("time"));%></td>
<td><%out.print(rs.getString("date"));%></td>
</tr>
<%
}
%>
</table>
<%
st.close();
}catch(Exception e1) { System.out.println(e1);}
%>
</div>

<form action="transaction" method="post" autocomplete="off">
<div class="ftransfer">
<h1 class="fth">amount transfer</h1>

<div class="ftm">
<p class="fttf">Account Number : <input type="text" id="anif" class="ftif" name="acno" required> </p>
<p class="fttf">Beneficlary Name : <input type="text" id="bnif" class="ftif" name="bn" required> </p>
<p class="fttf">Amount to transfer : <input type="text" id="atif" class="ftif" name="att" required> </p>
<p class="fttf">Enter current Date : <input type="date" id="date" class="ftif" name="ecd" required> </p>
</div>

<div class="ftf">
<input type="submit" value="send" id="sb2" name="submit1" onclick="refresh()">
<input type="reset" value="cancel" id="cb">
</div>
</div>
</form>

<p class="text">Key Updates</p>
<div class="rightb" onclick="rb()">></div>

<div class="cards">
<div id="card1"></div>
<div id="card2"></div>
<div id="card3"></div>
</div>

</main>

<footer>

<div class="fd">
<p class="smh" style="background-color:#3b5999"></p>
<p class="smh" style="background-color:#25d366;color:white"></p>
<p class="smh" style="background-color:#cd201f;color:white"></p>
<p class="smh" style="background-color:#00aced"></p>
</div>
<div class="fd1">
</div>
<div class="fd2">
</div>

</footer>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>


var adc1=document.querySelector('.mcard1');
var adc2=document.querySelector('.mcard2');
var adc3=document.querySelector('.mcard3');
var x2=document.querySelector('.alert2');
var x3=document.querySelector('table');
var main=document.querySelector('main');
var c1=document.querySelector('#card1');
var c2=document.querySelector('#card2');
var c3=document.querySelector('#card3');
var y=document.querySelector('.slide');
var z=document.querySelector('.icon');
var r=document.querySelector('.return');
var ft=document.querySelector('.ftransfer');
var ft1=document.querySelector('.dftt');
var hc1=document.querySelector('.text');
var hc2=document.querySelector('.rightb');
var hc3=document.querySelector('.cards');
var m1=document.getElementById('am2');
var m2=document.getElementById('am1');
var c=0;
var btn1=document.querySelector('.mbtn1');
var btn2=document.querySelector('.mbtn2');
var btn3=document.querySelector('.mbtn3');
var hc4=document.querySelector('.cmenu');
var hc5=document.querySelector('.amenuc');

function alert2()
{
main.setAttribute("style","height:70vh");
x2.setAttribute("style","display:flex");
window.setTimeout("back()",100);
window.setTimeout("stoptf()",0);
}

function stop()
{
x2.setAttribute("style","display:none");
}

function menu()
{
y.setAttribute("style","left:0%");
z.setAttribute("style","display:none");
r.setAttribute("style","left:20%");
}


function back()
{
y.setAttribute("style","left:-20%");
z.setAttribute("style","display:flex");
r.setAttribute("style","left:-22%");
}

function rb()
{
c++;

if(c==1)
{
c1.setAttribute("style","transform:rotate(90deg) translateY(-43%);opacity:0;filter:blur(10px);z-index:1");
c2.setAttribute("style","transform:rotate(0deg) translateY(0%);opacity:1;filter:blur(0px);z-index:3");
c3.setAttribute("style","transform:rotate(0deg) translateY(0%);opacity:0;filter:blur(0px);z-index:2");
}

if(c==2)
{
c2.setAttribute("style","transform:rotate(90deg) translateY(-43%);opacity:0;filter:blur(10px);z-index:1");
c3.setAttribute("style","transform:rotate(0deg) translateY(0%);opacity:1;filter:blur(0px);z-index:3");
c1.setAttribute("style","transform:rotate(0deg) translateY(0%);opacity:0;filter:blur(0px);z-index:2");
}


if(c==3)
{
c3.setAttribute("style","transform:rotate(90deg) translateY(-43%);opacity:0;filter:blur(10px);z-index:1");
c1.setAttribute("style","transform:rotate(0deg) translateY(0%);opacity:1;filter:blur(0px);z-index:3");
c2.setAttribute("style","transform:rotate(0deg) translateY(0%);opacity:0;filter:blur(0px);z-index:2");
c=0;
}

}

function showtf()
{
m1.innerHTML="transfer";
m2.innerHTML="Home";
m1.setAttribute("style","background-color:orange;color:white;border-radius:20% 20% 0% 0%/40% 40% 0% 0%");
ft.setAttribute("style","display:flex");
ft1.setAttribute("style","display:none");
hc1.setAttribute("style","display:none");
hc2.setAttribute("style","display:none");
hc3.setAttribute("style","display:none");
hc4.setAttribute("style","display:none");
main.setAttribute("style","height:70vh");
hc5.setAttribute("style","display:none");
window.setTimeout("back()",100);
}

function showtfh()
{
m1.innerHTML="transactions";
m2.innerHTML="Home";
m1.setAttribute("style","background-color:orange;color:white;border-radius:20% 20% 0% 0%/40% 40% 0% 0%;font-size:12px");
ft.setAttribute("style","display:none");
ft1.setAttribute("style","display:flex");
hc1.setAttribute("style","display:none");
hc2.setAttribute("style","display:none");
hc3.setAttribute("style","display:none");
hc4.setAttribute("style","display:none");
hc5.setAttribute("style","display:none");
var hcv=x3.offsetHeight;
var hcv1=hcv+30+"px";
if(hcv>=520)
{
main.style.height=hcv1;
console.log(hcv1);
}
window.setTimeout("back()",100);
}

function stoptf()
{
m1.innerHTML="";
m2.innerHTML="";
m1.setAttribute("style","background-color:white");
ft.setAttribute("style","display:none");
hc1.setAttribute("style","display:flex");
hc2.setAttribute("style","display:flex");
hc3.setAttribute("style","display:flex");
hc4.setAttribute("style","display:flex");
hc5.setAttribute("style","display:flex");
main.setAttribute("style","height:70vh");
ft1.setAttribute("style","display:none");
window.setTimeout("back()",100);
}

function showac1()
{
adc1.setAttribute("style","left:-10%;width:10%;z-index:1");
adc2.setAttribute("style","left:0;width:100%;z-index:2");
adc3.setAttribute("style","left:100%;width:10%;z-index:1");
btn1.setAttribute("style","border-bottom:none;color:black");
btn2.setAttribute("style","border-bottom:3px solid orange;color:darkorange");
btn3.setAttribute("style","border-bottom:none;color:black");
rb2.setAttribute("style","background-color:orange");
rb1.setAttribute("style","background-color:white");
rb3.setAttribute("style","background-color:white");
t1=setTimeout("showac2()",4000);
}

function showac2()
{
adc2.setAttribute("style","left:-10%;width:10%;z-index:1");
adc3.setAttribute("style","left:0;width:100%;z-index:2");
adc1.setAttribute("style","left:100%;width:10%;z-index:1");
btn2.setAttribute("style","border-bottom:none;color:black");
btn3.setAttribute("style","border-bottom:3px solid orange;color:darkorange");
btn1.setAttribute("style","border-bottom:none;color:black");
rb1.setAttribute("style","background-color:white");
rb3.setAttribute("style","background-color:orange");
rb2.setAttribute("style","background-color:white");
t2=setTimeout("showac3()",4000);
}

function showac3()
{
adc3.setAttribute("style","left:-10%;width:10%;z-index:1");
adc1.setAttribute("style","left:0;width:100%;z-index:2");
adc2.setAttribute("style","left:100%;width:10%;z-index:1");
btn2.setAttribute("style","border-bottom:none;color:black");
btn1.setAttribute("style","border-bottom:3px solid orange;color:orange");
rb3.setAttribute("style","background-color:white");
rb2.setAttribute("style","background-color:white");
rb1.setAttribute("style","background-color:orange");
btn3.setAttribute("style","border-bottom:none;color:black");
t3=setTimeout("showac1()",4000);
}

function showabc1()
{
window.setTimeout("showac3()",100);
clearTimeout(t1);
clearTimeout(t2);
clearTimeout(t3);
}

function showabc2()
{
window.setTimeout("showac1()",100);
clearTimeout(t1);
clearTimeout(t2);
clearTimeout(t3);
}

function showabc3()
{
window.setTimeout("showac2()",100);
clearTimeout(t1);
clearTimeout(t2);
clearTimeout(t3);
}

ft1.setAttribute("style","display:none");
window.setTimeout("showac1()",2000);
</script>
</body>
</html>



