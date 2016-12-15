<style>
    #details{
        display: none;
        width: 450px;
        margin-bottom: 0px; 
        margin-top: -9px;
        background-color: white; 
        position: fixed 
    }
    #details-btn{
        z-index:10;
        padding-top: 71px;
    }
    #btnin{
        border-top-right-radius: 5px;
        border-top-left-radius: 5px;
        background-color: #4d4d4d;
        margin-top: 2px;
        padding-top: 1px;
        padding-left: 10px;
        padding-right: 10px;
        font-family: sans-serif;
        font-size: 12px;
        color: white;
        text-align: center;
        width: auto;
        float: right;
        margin-left: 6px;
    }
    #linka {
        font-family: sans-serif;
        color: #cccccc;
        text-decoration: none;
        font-weight: bold;
        text-shadow: 0 2px 1px black;
    }
    #detailsall{
        background: #4d4d4d;
        z-index: 100;
        text-align: right;
        padding-top:  2px; 
        font-family: sans-serif;
        font-weight: bold;
        color: #cccccc;
        text-shadow: 0 2px 1px black;
        height: 19px;
    }
</style>

<script>
    $(document).ready(function () {
        $(".btn1").click(function () {
            $("#lastlog").slideToggle(0);
        });
        $("#mainheader").mouseleave(function () {
            $("#lastlog").hide();
        });

    });
    function changepasshover() {
        document.getElementById("changepassword").className = "fa fa-unlock-alt";

    }
    function changepassout() {
        document.getElementById("changepassword").className = "fa fa-lock";
    }
    function login() {
        document.getElementById("login_out").className = "fa fa-chevron-circle-right";
    }
    function logout() {
        document.getElementById("login_out").className = "fa fa-chevron-circle-left";
    }

</script>
<div id="mainheader" class="navbar navbar-inverse navbar-fixed-top" style="padding-left: 0px;padding-right: 0px;padding-bottom: 0px ; background-color: white; margin-bottom: 0px">   
    <div class="main_header" style="padding-left: 0px;padding-right: 0px;padding-bottom: 0px">
        <div class="row" style="padding-left: 0px;padding-right: 0px;background-color: white;margin-top: -14px">
            <div class="col-sm-4">
                <img class="logo" style="width:487px ; height: 87; padding-left: 0px;padding-right: 0px;padding-top: 0px;padding-bottom: 0px;margin-top: 4px;margin-left: -43px" alt="ntb logo" src="resources/images/Slogo6.png" />               
            </div>
            <div class="col-sm-2"></div>
            <!--<div class="col-sm-2"></div>-->
            <div class="col-sm-6">
                <div id="details-btn">
                    <div id="btnin">
                        <a title="Change Password" href="ViewChangePassword.action?message=error3" id="linka" class="btn1" onmouseover="changepasshover();" onmouseout="changepassout();"><span style="color: #cccccc;">Change Password&nbsp&nbsp&nbsp<i id="changepassword" class="fa fa-lock" aria-hidden="true"></i></span></a>
                    </div>
                    <div id="btnin">
                        <a title="Logout" href="LogoutUserLogin.action?message=error3" id="linka" class="btn2" onmouseover="login();" onmouseout="logout();"><span style="color: #ffcc33;">Logout&nbsp&nbsp&nbsp<i id="login_out" class="fa fa-chevron-circle-left" aria-hidden="true"></i></span></a>
                    </div>
                    <div id="btnin">
                        <a title="View Last Login details" href="#" id="linka" class="btn1"><span style="color:#ffffff;">Last Login Details&nbsp&nbsp&nbsp<i class="fa fa-info-circle" aria-hidden="true"></i></span></a>
                    </div>
                </div>
            </div>						
        </div>
    </div> 
    <div id="detailsall">
        <span id="lastlog" style="padding-right: 150px;display: none;" >Last login date time:<span style="color: white;"> ${LOGGEDDATE}</span></span>
        <span style="margin-right: 450px;"><span style="padding-right: 10px;" > Welcome,&nbsp;<span style="color: #ececec;">${SYSTEMUSER.username} </span>&nbsp&nbsp|&nbsp&nbsp User Role: <span style="color: #ececec;">${SYSTEMUSER.userrole.description}</span>
            </span><span style="color:white;padding-right: 10px;">|&nbsp&nbsp&nbsp${CURRENTDATE}</span></span>
    </div>
    <div style=" background: #EE7202;box-shadow: 0 2px 5px #908e8e;height: 5px;z-index: 10;"></div>
</div>
