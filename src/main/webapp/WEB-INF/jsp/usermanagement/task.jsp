<%-- 
    Document   : task
    Created on : Jan 3, 2016, 11:09:04 AM
    Author     : chanuka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <script type="text/javascript">

            var oTable;

            $(document).ready(function () {

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");

                var stringify_aoData = function (aoData) {
                    var o = {};
                    var modifiers = ['mDataProp_', 'sSearch_', 'iSortCol_', 'bSortable_', 'bRegex_', 'bSearchable_', 'sSortDir_'];
                    jQuery.each(aoData, function (idx, obj) {
                        if (obj.name) {
                            for (var i = 0; i < modifiers.length; i++) {
                                if (obj.name.substring(0, modifiers[i].length) == modifiers[i]) {
                                    var index = parseInt(obj.name.substring(modifiers[i].length));
                                    var key = 'a' + modifiers[i].substring(0, modifiers[i].length - 1);
                                    if (!o[key]) {
                                        o[key] = [];
                                    }
                                    //  console.log('index=' + index);
                                    o[key][index] = obj.value;
                                    //console.log(key + ".push(" + obj.value + ")");
                                    return;
                                }
                            }
                            // console.log(obj.name+"=" + obj.value);
                            o[obj.name] = obj.value;
                        }
                        else {
                            o[idx] = obj;
                        }
                    });
                    return JSON.stringify(o);
                };

                oTable = $('#table').dataTable({
                    "bProcessing": true,
                    "bServerSide": true,
                    "sAjaxSource": "${pageContext.servletContext.contextPath}/listTask",
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        //    	alert(token);
                        aoData.push({'name': 'csrf_token', 'value': token});
                        aoData.push({'name': 'header', 'value': header});
                        $.ajax({
                            dataType: 'json',
                            contentType: "application/json;charset=UTF-8",
                            type: 'POST',
                            url: "${pageContext.servletContext.contextPath}/listTask",
                            data: stringify_aoData(aoData),
                            success: fnCallback,
                            error: function (e) {
                                //                                alert(e);
                                window.location = "${pageContext.request.contextPath}/login.jsp";
                            }
                        });
                    },
                    //                    "fnRowCallback": function(nRow, aData, iDisplayIndex) {
                    //                        if (iDisplayIndex % 2 == 1)
                    //                            nRow.className = "gradeA odd";
                    //                        else
                    //                            nRow.className = "gradeA even";
                    //                        return nRow;
                    //                    },
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers",
                    bDeferRender: true,
                    responsive: true,
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
                            "mDataProp": "statusDes"
                        },
                        {
                            "title": "Created Time",
                            "targets": 3,
                            "mDataProp": "createdtimeStr"
                        }, {
                            "title": "Edit",
                            "visible": false,
                            sortable: false,
                            //                            "sClass": "testclass",
                            "render": function (data, type, full, meta) {
                                return '<a title="Edit Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="editTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-edit"></span></a>';
                            },
                            "targets": 4
                        }, {
                            "title": "Delete",
                            sortable: false,
                            //                            "sClass":"testclass",
                            "render": function (data, type, full, meta) {
                                return '<a title="Delete Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="deleteTaskInit(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-remove"></span></a>';
                            },
                            "targets": 5

                        }],
                    "fnInitComplete": function (oSettings, json) {
//                                $(oTable).parents('.dataTables_wrapper').first().find('thead').hide();                                

                    }

                });
            }); // end of document.ready function


            //-------------------------------------------------------------------------------------------------------------------------------


            //            var oTable;
            //            $(document).ready(function() {
            //
            //                var token = $("meta[name='_csrf']").attr("content");
            //                var header = $("meta[name='_csrf_header']").attr("content");
            //                var stringify_aoData = function(aoData) {
            //                    var o = {};
            //                    var modifiers = ['mDataProp_', 'sSearch_', 'iSortCol_', 'bSortable_', 'bRegex_', 'bSearchable_', 'sSortDir_'];
            //                    jQuery.each(aoData, function(idx, obj) {
            //                        if (obj.name) {
            //                            for (var i = 0; i < modifiers.length; i++) {
            //                                if (obj.name.substring(0, modifiers[i].length) == modifiers[i]) {
            //                                    var index = parseInt(obj.name.substring(modifiers[i].length));
            //                                    var key = 'a' + modifiers[i].substring(0, modifiers[i].length - 1);
            //                                    if (!o[key]) {
            //                                        o[key] = [];
            //                                    }
            //                                    //  console.log('index=' + index);
            //                                    o[key][index] = obj.value;
            //                                    //console.log(key + ".push(" + obj.value + ")");
            //                                    return;
            //                                }
            //                            }
            //                            // console.log(obj.name+"=" + obj.value);
            //                            o[obj.name] = obj.value;
            //                        }
            //                        else {
            //                            o[idx] = obj;
            //                        }
            //                    });
            //                    return JSON.stringify(o);
            //                };
            //
            //
            //                oTable = $('#table').dataTable({
            //                    "bProcessing": true,
            //                    "bServerSide": true,
            //                    "sAjaxSource": "${pageContext.servletContext.contextPath}/listTask",
            //                    "fnServerData": function(sSource, aoData, fnCallback) {
            //                        //    	alert(token);
            //                        aoData.push({'name': 'csrf_token', 'value': token});
            //                        aoData.push({'name': 'header', 'value': header});
            //
            //                        $.ajax({
            //                            dataType: 'json',
            //                            contentType: "application/json;charset=UTF-8",
            //                            type: 'POST',
            //                            url: "${pageContext.servletContext.contextPath}/listTask",
            //                            data: stringify_aoData(aoData),
            //                            success: fnCallback,
            //                            error: function(e) {
            ////                                alert(e);
            //                                window.location = "${pageContext.request.contextPath}/login.jsp";
            //                            }
            //                        });
            //                    },
            //                    "fnRowCallback": function(nRow, aData, iDisplayIndex) {
            //                        if (iDisplayIndex % 2 == 1)
            //                            nRow.className = "gradeA odd";
            //                        else
            //                            nRow.className = "gradeA even";
            //                        return nRow;
            //                    },
            //                    "bJQueryUI": true,
            //                    "sPaginationType": "full_numbers",
            //                    bDeferRender: true,
            //                    responsive: true,
            //                    "aoColumns": [
            //                        {"mDataProp": "taskCode", "bVisible": true},
            //                        {"mDataProp": "description", "bVisible": true},
            //                        {"mDataProp": "statusDes", "bVisible": true},
            //                        {"mDataProp": "createdtimeStr", "bVisible": true},
            //                        {"mDataProp": "taskCode", "bVisible": true, "bSortable": false},
            //                        {"mDataProp": "taskCode", "bVisible": true, "bSortable": false}
            //                    ],
            //                    "aoColumnDefs": [
            //                        {
            //                            "aTargets": [4],
            //                            "mRender": function(data, type, full) {
            //                                return '<a title="Edit Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="editTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-edit"></span></a>';
            //                            }
            //
            //                        },
            //                        {
            //                            "aTargets": [5],
            //                            "mRender": function(data, type, full) {
            //                                return '<a title="Delete Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="deleteTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-remove"></span></a>';
            //                            }
            //
            //                        }
            //
            //                    ]
            //                });
            //            }); // end of document.ready function
            //
            //






            //-------------------------------------------------------------------------------------------------------------------------------



            //            var oTable;
            //
            //            $(document).ready(function() {
            //                oTable = $("#table").DataTable({
            //                    "data": "",
            //                    "pDestroy": true,
            //                    "ajax": {
            //                        "url": "listTask",
            //                        "type": "post",
            //                        "data": "",
            //                        "dataSrc": function(data) {
            //                            return data;
            //                        }
            //                    },
            //                    "columnDefs": [
            //                        {
            //                            "title": "Task Code",
            //                            "targets": 0,
            //                            "mDataProp": "taskCode"
            //                        },
            //                        {
            //                            "title": "Description",
            //                            "targets": 1,
            //                            "mDataProp": "description"
            //                        },
            //                        {
            //                            "title": "Status",
            //                            "targets": 2,
            //                            "mDataProp": "status"
            //                        },
            //                        {
            //                            "title": "Created Time",
            //                            "targets": 3,
            //                            "mDataProp": "createdtimeStr"
            //                        }, {
            //                            "title": "Edit",
            //                            sortable: false,
            //                            "render": function(data, type, full, meta) {
            //
            //                                return '<a title="Edit Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="editTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-edit"></span></a>';
            //                            },
            //                            "targets": 4
            //                        }, {
            //                            "title": "Delete",
            //                            sortable: false,
            //                            "render": function(data, type, full, meta) {
            //                                return '<a title="Delete Task" id=' + full.taskCode + ' class="btn btn-primary btn-xs  editor_retry"  onclick="deleteTask(\'' + full.taskCode + '\')"><span class="glyphicon glyphicon-remove"></span></a>';
            //                            },
            //                            "targets": 5
            //                        }],
            //                    "sPaginationType": "full_numbers",
            //                    "bJQueryUI": true,
            //                    bDeferRender: true,
            //                    responsive: true
            //                });
            //            });
            //

            function deleteTaskInit(keyval) {

                BootstrapDialog.show({
                    title: 'Delete Task',
                    message: 'Are you sure you want to delete task ' + keyval + '?',
                    buttons: [{
                            label: 'OK',
                            action: function (dialog) {
                                deleteTask(keyval);
                                dialog.close();
                            }
                        }, {
                            label: 'Close',
                            cssClass: 'btn-primary',
                            action: function (dialog) {
                                dialog.close();
                            }
                        }]
                });


            }

            function editTask(keyval) {

                $.ajax({
                    type: "POST",
                    url: "findTask",
                    data: {taskCode: keyval},
                    dataType: 'json',
                    imeout: 10000,
                    success: function (data) {
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
                    error: function (data) {
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
                    success: function (data) {
//                                alert(data.message);
                        //                        oTable.ajax.reload();

                        BootstrapDialog.show({
                            title: 'Deleting Process.',
                            message: data.message,
                            buttons: [{
                                    label: 'OK',
                                    cssClass: 'btn-primary',
                                    action: function (dialogItself) {
                                        dialogItself.close();

                                    }
                                }]
                        });

                        oTable.fnDraw();
                        resetAllData();
                    },
                    error: function (data) {
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
                oTable.fnDraw();
            }
        </script>
        <style>
            .testclass {
                display:none;
            }
        </style>
        <title></title>
    </head>
    <body style="">

        <div id="divmsg">
            <!-- success Message -->
            <c:set var="successmsg" value="${successMessage}" />
            <c:if test="${not empty successmsg}">
                <div id="msgsuccess" class="icon" style="background-color: #E5FEDA;color: green;font-weight: bold;padding: 5px;border-style: solid;border-color: green;border-width: 1px; ">
                    <span class="tile-title message-success">
                        <c:out value="${successMessage}" /></span>
                </div>
            </c:if>
            <!-- Error Message -->
            <c:set var="errorMessage" value="${errorMessage}" />
            <c:if test="${not empty errorMessage}">
                <div id="msgerror" class="icon " style="background-color: #FEE5E7;color: red;font-weight: bold;padding: 5px;border-style: solid;border-color: red;border-width: 1px;">
                    <span class="tile-title message-error">
                        <c:out value="${errorMessage}" />
                    </span>
                </div>
            </c:if>
            <!-- Form Error Message -->
            <%--<form:errors path="tasksearchform.*"/>--%>
            <c:set var="domainNameErrors"><form:errors path="tasksearchform.*"/></c:set>
            <c:if test="${not empty domainNameErrors}">
                <div id="msgerror" class="icon " style="background-color: #FEE5E7;color: #DF2834;font-weight: bold;padding: 5px;border-style: solid;border-color: #DF2834;border-width: 1px;">
                    <span class="tile-title message-error">
                        ${domainNameErrors}
                    </span>
                </div>
            </c:if>
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
                                       <c:if test="${tasksearchform.isAddBut}">disabled</c:if>
                                           value="Add" 
                                           id="addButton"
                                           style="border-radius: 12px;background-color:#969595;color:white;"
                                           />
                                </div> 
                                <div class="form-group">
                                    <input type="submit" class="form-control btn_normal" 
                                    <c:if test="${tasksearchform.isUpdateBut}">disabled</c:if>
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

                <!--                <table class="display" id="table">
                                    <thead>
                                        <th class="heading">Task Code</th>
                                        <th class="heading">Description</th>
                                        <th class="heading">Status</th>
                                        <th class="heading">Created Time</th>
                <%--<c:if test="${tasksearchform.isAddBut}"><th class="heading">Edit</th></c:if>--%>
            <th class="heading">Edit</th>
            <th class="heading">Delete</th>
        </thead>
        <tbody>
        </tbody>

    </table>-->
            </div>
        </div>

        <!-- end: PAGE CONTENT-->

    </body>
</html>