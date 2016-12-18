<%-- 
    Document   : home
    Created on : Sep 14, 2016, 5:11:38 PM
    Author     : dilanka_w
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="/WEB-INF/jsp/template/stylelinks.jspf" %>
        <title>Home Page</title>
        <script>
        </script>
    </head>

    <body>
        <tiles:insertAttribute name="header" />
        <%--<jsp:include page="/header.jsp"/>--%>
        <div class="main-container">
            <tiles:insertAttribute name="navbar" />

            <%--<jsp:include page="/leftmenu.jsp"><jsp:param name="firstpage" value="0"/></jsp:include>--%>
            <tiles:insertAttribute name="menu" />

            <div class="main-content">

                <div class="container" style="min-height: 760px;">
                    <div class="row">
                        <div id="content1">

                            <%--<tiles:insertAttribute name="message" />--%>


                            <tiles:insertAttribute name="body" /></div>  
                    </div> 
                </div> 
            </div>
            <!-- end: PAGE CONTENT-->
        </div>
        </div>
        <!-- end: PAGE -->
        <!-- end: MAIN CONTAINER -->
        <!-- start: FOOTER -->
        <tiles:insertAttribute name="footer" />
        <%--<jsp:include page="/footer.jsp"/>--%>
        <!-- end: FOOTER -->
        <!-- end: BODY -->
    </body>
</html>
