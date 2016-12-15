<%-- 
    Document   : welcomepage
    Created on : dec 04, 2016, 1:45:39 PM
    Author     : chanuka
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

        <title>Sampath SMS Solution</title>

        <%@include file="/stylelinks.jspf" %>
    </head>
    <script type="text/javascript">

        function myFunction() {
            window.open("${pageContext.request.contextPath}/login.jsp");
        }

    </script>
    <style>
        img.displayed{
            display: block;
            margin-left: auto;
            margin-right: auto;
            margin-top: auto;
            margin-bottom: auto;

        }
    </style>
    <body>

        <img class="displayed" alt="welcome image" height="552" src="${pageContext.request.contextPath}/resources/images/welcome5.png" width="1313" usemap="#loginmap" />
        <map name="loginmap">
            <area shape="rect" coords="250,200,1100,340" alt="text" onclick="myFunction()" href="#"  >
        </map>

    </body>
</html>
