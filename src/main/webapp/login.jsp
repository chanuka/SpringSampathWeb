<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" href="resources/css/font-awesome.min.css">

        <script type="text/javascript">
            function encryp() {
                if ($('#password').val() != "") {
                    var ps = $('#password').val();
                    $('#password').val(CryptoJS.MD5(ps));
                    //                    var value = '&lt;%= request.getMethod() %&gt;';
                    //                    alert(CryptoJS.MD5(ps));

                }
            }

        </script>     
        <style type="text/css">
            .main{
                position: absolute;
                margin: auto;
                top: 25%; left: 0; bottom: 0; right: 0;
            }
            .error-dis{
                text-align: center;                   
            }
            .copyright{
                bottom: 0;
                left: 0;
                position: fixed;
                right: 0;
                z-index: 1000;
                border-top-width: 1px;
                text-align: center;
            }
            .login-form{
                display: block;
                margin-left:20px;
                margin-bottom:20px;
                margin-right: 20px;
            }
            body{
                background-color: #D3D3D3;
            }

        </style>
    </head>
    <!-- end: HEAD -->
    <!-- start: BODY -->
    <body>
        <!-- start: LOGIN BOX -->         
        <div class="container">
            <div class="row">
                <div class="main">
                    <div class="col-md-4 col-md-offset-4">

                        <div>
                            <div class="panel panel-info" style="border-color: #D3D3D3;background-color: #D3D3D3" >
                                <div>
                                    <div class="row">
                                        <img src="resources/images/Slogo9.png" alt="NTB Logo" style="margin-top: 15px;
                                             margin-bottom: -28px;margin-left: -40px; width: 475px;height: 110px;">                                    
                                    </div>						
                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form id="login-form" name="login-form" class="login-form" novalidate="novalidate" action="CheckUserLogin" method="post">
                                                <div class="input-group form-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user" style="color:#999999;"></i></span>
                                                    <input type="text" class="form-control" placeholder="userName" name="userName">
                                                </div>
                                                <div class="input-group form-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" style="color:#999999;"></i></span>
                                                    <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                                                </div>									
                                                <div class="form-group">
                                                    <input type="submit" class="btn btn-sm form-control" value="LOGIN" style="background-color:gray;color:white;font-weight:bold;">
                                                </div>									
                                            </form>								
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="divmsg">
                                <!-- success Message -->
                                <c:set var="successmsg" value="${successMessage}" />
                                <c:if test="${not empty successmsg}">
                                    <div id="msgsuccess" class="icon">
                                        <span class="tile-title message-success"><c:out
                                                value="${successMessage}" /></span>
                                    </div>
                                </c:if>
                                <!-- Error Message -->
                                <c:set var="errorMessage" value="${errorMessage}" />
                                <c:if test="${not empty errorMessage}">
                                    <div id="msgerror" class="icon">
                                        <span class="tile-title message-error">
                                            <c:out value="${errorMessage}" />
                                        </span>
                                    </div>
                                </c:if>
                            </div>

                        </div>
                    </div>	
                </div>			
            </div>
        </div>


        <!-- start: COPYRIGHT -->
        <div class="copyright" style="background-color: gray">
            <!--<font id="versionno"></font>-->
            <span style="font-family: sans-serif; color: white; font-size: 12px ; text-shadow: 0 0 5px black;">
                <%                String param1 = application.getInitParameter("version");
                    out.println(param1);
                %>

                Copyright © 2016 <a href="http://www.epiclanka.net/"><span style="color: black; text-shadow: 0 0 0 black;">Epic Lanka (pvt) Ltd.</a> All rights reserved.</span>
        </div>
        <!-- end: COPYRIGHT -->
    </body><!-- end: BODY -->
</html>