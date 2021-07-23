<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
response.setHeader("cache-Control","no-cache,no-store,must-revalidate");

response.setHeader("pragma","no-cache");

response.setHeader("Expires","0");

if(session.getAttribute("userid")!=null)
{
	response.setContentType("text/html");
	out.println("<script type=\"text/javascript\">");
	out.println("alert('you are already login');");
	out.println("window.close();");
	out.println("</script>");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>
bank home page
</title>
<link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>

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
<li><p class="hmt" onclick="alert2()">option1</p></li>
<li><p class="hmt" onclick="alert2()">option2</p></li>
<li><p class="hmt" onclick="alert2()">option3</p></li>
<li><p class="hmt" onclick="alert2()">option4</p></li>
<li><p class="hmt" onclick="alert2()">option5</p></li>
</ul>

<ul id="hmenu2">
<li><p class="hmt1" id="am1" onclick="stoptf()"></p></li>
<li><p class="hmt1" id="am2"></p></li>
<li><p class="hmt1" id="am3"></p></li>
</ul>

<span class="icon" onclick="menu()">></span>
<div class="slide">
<ul class="menu">
<li class="selected">online banking</li>
<li class="li" onclick="alert2()"><p class="hcc">menu 1</p></li>
<li class="li" onclick="alert2()"><p class="hcc">amount balance?</p></li>
<li class="li" onclick="alert2()"><p class="hcc">fund transfer</p></li>
<li class="li" onclick="alert2()"><p class="hcc">menu 4</p></li>
<li class="li" onclick="alert2()"><p class="hcc">menu 5</p></li>
<li class="li" onclick="alert2()"><p class="hcc">menu 6</p></li>
</ul>
</div>
<span class="return" onclick="back()"><</span>

<div class="alert1" >
<div class="ialert1">

<img src="images/Xb.png" id="img1" onclick="stop()">

<form action="login" method="post" autocomplete="off">
<p id="at">USER ID:</p>
<input type="text" id="tif" name="tif" required>
<p id="pt">PASSWORD:</p>
<input type="password" id="nif" name="nif" required>
<input type="submit" value="submit" id="sb" name="submit"> 
</form>

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

<button class="hlb1" onclick="salert()">sign in</button>
<button class="hlb2" onclick="alert1()">login</button>

</header>

<main>


<form action="signin" method="Post" autocomplete="off">
<div class="rform" >

<img src="images/bankimg.png" id="img2">

<div id="form">
<p class="fht">Get started with online banking</p>
<input type="text" id="first" name="name" placeholder="Enter your name*" required>
<input type="password" id="sec" name="password" placeholder="Choose your password*" required>
<input type="text" id="third" name="email" placeholder="Email ID*" required>
<input type="password" id="fourth" name="cpassword" placeholder="Confirm password*" required>
<input type="text" id="five" name="userid" placeholder="User ID*" required>
<input type="text" id="six" name="ACNO" placeholder="Account No*" required>

<input type="submit" value="submit" id="submit" name="submit" onclick="check()">
<input type="reset" value="reset" id="check" > 
</div>
</div>
</form>

<p class="text" id="m2">Key Updates</p>
<div class="rightb" onclick="rb()" id="m3">></div>

<div class="cards" id="m1">
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
var rb1=document.querySelector('#rb1');
var rb2=document.querySelector('#rb2');
var rb3=document.querySelector('#rb3');
var adc3=document.querySelector('.mcard3');
var sf=document.querySelector('.rform');
var x1=document.querySelector('.alert1');
var c1=document.querySelector('#card1');
var c2=document.querySelector('#card2');
var c3=document.querySelector('#card3');
var y=document.querySelector('.slide');
var z=document.querySelector('.icon');
var r=document.querySelector('.return');
var hc1=document.getElementById('m1');
var hc2=document.getElementById('m2');
var hc3=document.getElementById('m3');
var img1=document.getElementById('img2');
var c=0;
var btn1=document.querySelector('.mbtn1');
var btn2=document.querySelector('.mbtn2');
var btn3=document.querySelector('.mbtn3');
var hc4=document.querySelector('.cmenu');
var hc5=document.querySelector('.amenuc');
var m1=document.getElementById('am2');
var m2=document.getElementById('am1');

function alert1()
{
x1.setAttribute("style","display:flex");
}

function alert2()
{
swal('','please get login first','images/no.jpeg');
window.setTimeout("back()",100);
}

function stop()
{
x1.setAttribute("style","display:none");
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

function salert()
{
m1.innerHTML="transfer";
m2.innerHTML="Home";
m1.setAttribute("style","background-color:#EDA01D;color:white;border-radius:20% 20% 0% 0%/40% 40% 0% 0%");	
sf.setAttribute("style","display:flex");
hc1.setAttribute("style","display:none");
hc2.setAttribute("style","display:none");
hc3.setAttribute("style","display:none");
hc4.setAttribute("style","display:none");
hc5.setAttribute("style","display:none");
window.setTimeout("back()",100);
}

function stoptf()
{
m1.innerHTML="";
m2.innerHTML="";
m1.setAttribute("style","background-color:white");
sf.setAttribute("style","display:none");
hc1.setAttribute("style","display:flex");
hc2.setAttribute("style","display:flex");
hc3.setAttribute("style","display:flex");
hc4.setAttribute("style","display:flex");
hc5.setAttribute("style","display:flex");
window.setTimeout("back()",100);
}

function showac1()
{
adc1.setAttribute("style","left:-10%;width:10%;z-index:1");
adc2.setAttribute("style","left:0;width:100%;z-index:2");
btn1.setAttribute("style","border-bottom:none;color:black");
btn2.setAttribute("style","border-bottom:2px solid darkorange;color:darkorange");
btn3.setAttribute("style","border-bottom:none;color:black");
rb2.setAttribute("style","background-color:orange");
rb1.setAttribute("style","background-color:white");
rb3.setAttribute("style","background-color:white");
adc3.setAttribute("style","left:100%;width:10%;z-index:1");
t1=setTimeout("showac2()",4000);
}

function showac2()
{
adc2.setAttribute("style","left:-10%;width:10%;z-index:1");
adc3.setAttribute("style","left:0;width:100%;z-index:2");
btn2.setAttribute("style","border-bottom:none;color:black");
btn3.setAttribute("style","border-bottom:2px solid orange;color:orange");
btn1.setAttribute("style","border-bottom:none;color:black");
rb1.setAttribute("style","background-color:white");
rb3.setAttribute("style","background-color:orange");
rb2.setAttribute("style","background-color:white");
adc1.setAttribute("style","left:100%;width:10%;z-index:1");
t2=setTimeout("showac3()",4000);
}

function showac3()
{
adc3.setAttribute("style","left:-10%;width:10%;z-index:1");
adc1.setAttribute("style","left:0;width:100%;z-index:2");
adc2.setAttribute("style","left:100%;width:10%;z-index:1");
btn2.setAttribute("style","border-bottom:none;color:black");
btn1.setAttribute("style","border-bottom:2px solid orange;color:orange");
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

alert('wellcome to our bank website');

window.setTimeout("showac1()",2000);
</script>
</body>
</html>



