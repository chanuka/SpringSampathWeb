<style>
    .ntb_navbar{
        padding: 10px 45px 15px 15px;;
        position: relative;
        height: 29px;
        font-size: 13px;
        margin-top: 5px;
        margin-left: -15px;
        font-weight: bold;
        /*color: #0080C7;*/
        font-family: sans-serif;
        color: #EE7202;
        list-style-type:none;
        
    }
    
    
</style>
<div class="row">
    <div class="col-sm-12">
        <ul class="ntb_navbar">
            <li>
            <h> ${CURRENTSECTION}</h> 
                <% if (session.getAttribute("CURRENTSECTION") != null && !session.getAttribute("CURRENTSECTION").equals("")) {%>
            &nbsp;<b><i class="fa fa-chevron-right" aria-hidden="true"></i></b>&nbsp;
            <% }%>
            <h>${CURRENTPAGE}</h>
            </li>				
        </ul>
    </div>
</div>