<%-- 
    Document   : task
    Created on : Jan 3, 2014, 11:09:04 AM
    Author     : chanuka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="/stylelinks.jspf" %>

        <script type="text/javascript">

            var oTable;

//            $(document).ready(
//                    function getTable() {
//                        $.ajax({
//                            type: "POST",
//                            contentType: "application/json",
//                            url: "listTask",
//                            dataType: 'json',
//                            imeout: 10000,
//                            success: function(data) {
//                                oTable = $("#table").DataTable({
//                                    "bFilter": true,
//                                    "aaData": data,
//                                    "bDestroy": true,
//                                    "columnDefs": [{
//                                            "title": "Task Code ",
//                                            "targets": 0
//                                        }, {
//                                            "title": "Description ",
//                                            "targets": 1
//                                        }, {
//                                            "title": "Status ",
//                                            "targets": 2
//                                        }, {
//                                            "title": "Created Time ",
//                                            "targets": 3
//                                        }, {
//                                            sortable: false,
//                                            "render": function(data, type, full, meta) {
//
//                                                return '<a title="Edit Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="editTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-edit"></span></a>';
//                                            },
//                                            "targets": 4
//                                        }, {
//                                            sortable: false,
//                                            "render": function(data, type, full, meta) {
//                                                return '<a title="Delete Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="deleteTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-remove"></span></a>';
//                                            },
//                                            "targets": 5
//                                        }],
//                                    "aoColumns": [{
//                                            "mDataProp": "taskCode"
//                                        }, {
//                                            "mDataProp": "description"
//                                        }, {
//                                            "mDataProp": "status"
//                                        }, {
//                                            "mDataProp": "createdtime"
//                                        }],
//                                    "bJQueryUI": true,
//                                    "sPaginationType": "full_numbers",
//                                    responsive: true
//                                });
//                            }
//
//                        });
//                    });
//
//


            $(document).ready(function() {
                oTable = $("#table").DataTable({
                    "data": "",
                    "pDestroy": true,
                    "ajax": {
                        "url": "listTask",
                        "type": "post",
                        "data": "",
                        "dataSrc": function(data) {
                            return data;
                        }
                    },
                    "columnDefs": [
                        {
                            "title": "Task Code",
                            "targets": 0,
                            "mDataProp": "taskCode"
                        },
                        {
                            "title": "Description",
                            "targets": 1,
                            "mDataProp": "description"
                        },
                        {
                            "title": "Status",
                            "targets": 2,
                            "mDataProp": "status"
                        },
                        {
                            "title": "Created Time",
                            "targets": 3,
                            "mDataProp": "createdtime"
                        }, {
                            "title": "Edit",
                            sortable: false,
                            "render": function(data, type, full, meta) {

                                return '<a title="Edit Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="editTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-edit"></span></a>';
                            },
                            "targets": 4
                        }, {
                            "title": "Delete",
                            sortable: false,
                            "render": function(data, type, full, meta) {
                                return '<a title="Delete Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="deleteTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-remove"></span></a>';
                            },
                            "targets": 5
                        }],
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true,
                    bDeferRender: true,
                    responsive: true
                });
            });



            function editTask(keyval) {

                $.ajax({
                    type: "POST",
                    url: "findTask",
                    data: {taskCode: keyval},
                    dataType: 'json',
                    imeout: 10000,
                    success: function(data) {
                        $('#divmsg').empty();
                        var msg = data.message;
                        if (msg) {
                            $('#taskCode').val("");
                            $('#taskCode').attr('readOnly', false);
                            $("#taskCode").css("color", "black");
                            $('#description').val("");
                            $('#status').val("");
                            $('#divmsg').text("");
                        }
                        else {
                            $('#taskCode').val(data.taskCode);
                            $('#taskCode').attr('readOnly', true);
                            $("#taskCode").css("color", "#858585");
                            $('#description').val(data.description);
                            $('#status').val(data.status);
                            $('#updateButton').prop('disabled', false);
                            $('#addButton').prop('disabled', true);
                        }
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/login.jsp";
                    }
                });
            }
            function deleteTask(keyval) {

                $.ajax({
                    type: "POST",
                    url: "deleteTask",
                    data: {taskCode: keyval},
                    dataType: 'json',
                    success: function(data) {
                        alert(data.message);
                        oTable.ajax.reload();
                        resetAllData();
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/login.jsp";
                    }
                });
            }
            function resetAllData() {

                $('#taskCode').val("");
                $('#divmsg').text("");
                $('#taskCode').attr('readOnly', false);
                $('#description').val("");
                $('#status').val("");
                $('#updateButton').prop('disabled', true);
                $('#addButton').prop('disabled', false);

            }
        </script>
        <title></title>
    </head>
    <body style="">
        <jsp:include page="/header.jsp"/>
        <div class="main-container">
            <jsp:include page="/leftmenu.jsp"/>
            <div class="main-content">
                <div class="container" style="min-height: 760px;">
                    <!-- start: PAGE NAVIGATION BAR -->
                    <jsp:include page="/navbar.jsp"/>
                    <!-- end: NAVIGATION BAR -->
                    <div class="row">
                        <div id="content1">

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
                                 <form:errors path="tasksearchform.*"/>
                            </div>
                            <div>

                                <div id="formstyle">


                                    <form:form class="form" id="tasksearch"
                                               name="tasksearch" action="addTask" theme="simple" method="post"
                                               modelAttribute="tasksearchform">

                                        <div class="row row_1">
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label>Task Code </label>
                                                    <form:input class="form-control" path="taskCode" type="text" name="taskCode" id="taskCode" maxLength="6" />

                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label>Description</label>
                                                    <form:input class="form-control" path="description" type="text" name="description" id="description" maxLength="64" />

                                                </div>
                                            </div>

                                            <div class="col-sm-3">
                                                <div class="form-group form-inline">
                                                    <label >Status</label>
                                                    <form:select id="status" multiple="false"
                                                                 class="form-control" path="status"
                                                                 >
                                                        <form:option value="" label="Select a Status" />
                                                        <c:forEach items="${tasksearchform.statusBeanList}"
                                                                   var="statusBean">
                                                            <form:option value="${statusBean.statusCode}"
                                                                         label="${statusBean.description}" />
                                                        </c:forEach>
                                                    </form:select>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row row_1 form-inline">
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <input type="submit" class="form-control btn_normal"
                                                           name="Add"
                                                           value="Add" 
                                                           id="addButton"
                                                           style="border-radius: 12px;background-color:#969595;color:white;"
                                                           />
                                                </div> 
                                                <div class="form-group">
                                                    <input type="submit" class="form-control btn_normal" 
                                                           disabled="true"
                                                           name="Update"
                                                           value="Update" 
                                                           id="updateButton"
                                                           style="border-radius: 12px;"
                                                           />
                                                </div> 
                                                <div class="form-group">
                                                    <button 
                                                        type="button" 
                                                        value="Reset" 
                                                        onclick="resetAllData()" 
                                                        class="form-control btn_normal"
                                                        style="border-radius: 12px;"
                                                        >Reset</button>

                                                </div>
                                            </div>

                                        </div>
                                    </form:form>

                                </div>



                                <div id="tablediv">

                                    <table id="table" >
                                        <thead>
                                            <tr>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                        <!-- end: PAGE CONTENT-->
                    </div>
                </div>
                <!-- end: PAGE -->
            </div>
            <!-- end: MAIN CONTAINER -->
            <!-- start: FOOTER -->
            <jsp:include page="/footer.jsp"/>
            <!-- end: FOOTER -->
            <!-- end: BODY -->
    </body>
</html>