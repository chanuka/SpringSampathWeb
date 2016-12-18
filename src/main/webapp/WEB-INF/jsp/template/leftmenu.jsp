<%@page import="com.epic.springsampathweb.bean.login.PageBean"%>
<%@page import="com.epic.springsampathweb.bean.login.SectionBean"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.epic.springsampathweb.util.common.SectionComparator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.epic.springsampathweb.util.varlist.SessionVarlist"%>
<head>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/main-responsive.css">
    <!--<link rel="stylesheet" href="resouces/css/all.css">-->
    <link rel="stylesheet" href="resources/css/bootstrap-colorpalette.css">
    <link rel="stylesheet" href="resources/css/perfect-scrollbar.css">
    <link rel="stylesheet" href="resources/css/theme_light.css" type="text/css" id="skin_color">

    <script>
        jQuery(document).ready(function() {
            Main.init();
            //            Index.init();
        });
    </script>

    <script type="text/javascript">

        $(document).ready(function() {

            $(document).ajaxStart(function() {
                $.blockUI({css: {
                        border: 'transparent',
                        backgroundColor: 'transparent'
                    },
                    message: '<img height="100" width="100" src="${pageContext.request.contextPath}/resources/images/loading2.gif" />',
                    baseZ: 2000
                });

            });

            $(document).ajaxStop(function() {
                $.unblockUI();
            });

        });



        function sectionClick(myid) {
            //            var idsec=$.cookie("selectedsec");
            //            var idsecval="#"+idsec;
            //            alert(idsecval);
            //            $(idsecval).removeClass("open");
            //            $(idsecval).removeClass("active open");
            //            $(idsecval).addClass("");


            $.cookie("selectedsec", myid, {path: "/", expires: 1});
        }

        function pageClick(myid) {
            //            
            //            var idpage=$.cookie("selectedpage");
            //            var idval="#"+idpage;
            //            $(idval).removeClass("active open");

            $.cookie("selectedpage", myid, {path: "/", expires: 1});

        }

    </script>

    <script type="text/javascript">
        jQuery(document).ready(function() {
            var firPage = ${param.firstpage} + "1";

            if (firPage === "1001") {
                $.cookie("selectedpage", 1001, {path: "/", expires: 1});
                $.cookie("selectedsec", 1, {path: "/", expires: 1});
                var firURL = $("a", '#1001').attr('href');
                window.location = firURL;
                //            window.location= firURL+'?operationMode=STORE';  
            }

            if (firPage === "01") {
                $.cookie("selectedpage", 1001, {path: "/", expires: -1});
                $.cookie("selectedsec", 1, {path: "/", expires: -1});
            }

            var idsec = $.cookie("selectedsec");
            var idsecval = "#" + idsec;
            $(idsecval).addClass("active open");
            //            $(idsecval).addClass("active open"); 
            var id = $.cookie("selectedpage");
            var idval = "#" + id;
            $(idval).addClass("active open");
            //                        $(idval).removeClass("active open");

        });
    </script>
</head>

<div>

    <div class="main-navigation navbar-collapse collapse"style=" border-right:solid #e6e5e5;border-width: 0.5pt;">


        <!-- remove leftmenu collapse feature   -->
        <!--<div class="navigation-toggler">-->
        <!--            <i class="clip-chevron-left"></i>
                    <i class="clip-chevron-right"></i>-->

        <!--</div>-->

        <div class="ntb_main">
            
           <i class="fa fa-bars" aria-hidden="true"></i>  Main Menu
        </div>
        <ul class="main-navigation-menu">


            <%

                try {

                    HashMap<SectionBean, List<PageBean>> sectionPageList = (HashMap<SectionBean, List<PageBean>>) request.getSession().getAttribute(SessionVarlist.SECTIONPAGELIST);
//                    String warnmsg = request.getSession().getAttribute(SessionVarlist.MIN_PASSWORD_CHANGE_PERIOD);
            %>



            <%
                    SectionComparator sec1 = new SectionComparator();
                    Set<SectionBean> section = new TreeSet<SectionBean>(sec1);

                    Set<SectionBean> section1 = sectionPageList.keySet();
                    for (SectionBean sec2 : section1) {
                        section.add(sec2);
                    }

                    int secId = 1;
                    int pageId = 1000;

                    out.println("<ul class=\'main-navigation-menu\' id=\'treemenu\' >");

                    for (SectionBean sec : section) {
                        int a = 0;
                        out.println("<li id=\'" + secId + "\'>");
                        out.println("<a href=\'javascript:void(0)\' onclick=\'sectionClick(" + secId + ")\'>");
                        out.println("<span class=\'title\'>" + sec.getDescription() + "</span><span class=\'glyphicon glyphicon-chevron-down pull-right\'></span>");
                        out.println("</a>");

                        out.println("<ul class=\'sub-menu\'>");

                        //                        out.println("d.add(" + i + "," + 0 + ",\'" + sec.getDescription() + "\');");
                        List<PageBean> pageList = sectionPageList.get(sec);
                        for (PageBean pageBean : pageList) {

                            pageId++;

                            out.println("<li id=\'" + pageId + "\'>");
                            out.println("<a href=\'" + request.getContextPath() + pageBean.getUrl() + "\'  onclick=\'pageClick(" + pageId + ")\'>");
                            out.println("<span class=\'title\'>" + pageBean.getDescription() + "</span>");
                            out.println("</a>");
                            out.println("</li>");

//                            out.println("d.add(" + j + "," + i + ",\'" + pageBean.getDescription() + "\'" + ",\' " + request.getContextPath() + pageBean.getUrl() + ".mpi\');");
                        }

                        out.println(" </ul>");
                        out.println(" </li>");

                        secId++;
                    }
                    out.println("</ul>");

                } catch (Exception ee) {

                    ee.printStackTrace();
                }
            %>


        </ul>

    </div></div>
