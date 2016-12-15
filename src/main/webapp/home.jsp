<%-- 
    Document   : home
    Created on : Sep 14, 2016, 5:11:38 PM
    Author     : dilanka_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="/stylelinks.jspf" %>
        <title>Home Page</title>
        <script>
        </script>
    </head>

    <body>

        <jsp:include page="/header.jsp"/>
        <div class="main-container">
            <jsp:include page="/leftmenu.jsp">
                <jsp:param name="firstpage" value="0"/>
            </jsp:include>
            <div class="main-content">

                <div class="container" style="min-height: 760px;">
                    <s:div>
                        <s:actionerror theme="jquery"/>
                        <s:actionmessage theme="jquery"/>
                    </s:div>
                    <!-- start: PAGE NAVIGATION BAR -->
                    <jsp:include page="/navbar.jsp"/>
                    <!-- end: NAVIGATION BAR -->
                    <img src="resouces/images/logosb.png" width="300px" height="300px" style="opacity: 0.2;margin-left: 25%; margin-top: 2%;"/>
                </div>
                <!-- end: PAGE CONTENT-->
            </div>
        </div>
        <!-- end: PAGE -->
        <!-- end: MAIN CONTAINER -->
        <!-- start: FOOTER -->
        <jsp:include page="/footer.jsp"/>
        <!-- end: FOOTER -->
        <!-- end: BODY -->
    </body>
</html>
