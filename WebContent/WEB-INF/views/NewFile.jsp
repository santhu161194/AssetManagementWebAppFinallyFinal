<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#flip-tabs{  
    width:300px;  
    margin:20px auto; position:relative;  
}  
#flip-navigation{  
    margin:0 0 10px; padding:0;   
    list-style:none;  
}  
#flip-navigation li{   
    display:inline;   
}  
#flip-navigation li a{  
    text-decoration:none; padding:10px;   
    margin-right:0px;  
    background:#f9f9f9;  
    color:#333; outline:none;  
    font-family:Arial; font-size:12px; text-transform:uppercase;  
}  
#flip-navigation li a:hover{  
    background:#999;   
    color:#f0f0f0;  
}  
#flip-navigation li.selected a{  
    background:#999;  
    color:#f0f0f0;  
}  
#flip-container{    
    width:300px;  
    font-family:Arial; font-size:13px;  
}  
#flip-container div{   
    background:#fff;  
}  


</style>
<script type="text/javascript">
$('document').ready(function(){  
    //initialize quickflip  
    $('#flip-container').quickFlip();  

    $('#flip-navigation li a').each(function(){  
        $(this).click(function(){  
            $('#flip-navigation li').each(function(){  
                $(this).removeClass('selected');  
            });  
            $(this).parent().addClass('selected');  
            //extract index of tab from id of the navigation item  
            var flipid=$(this).attr('id').substr(4);  
            //Flip to that content tab  
            $('#flip-container').quickFlipper({ }, flipid, 1);  

            return false;  
        });  
    });  
});  
</script>
<title>Insert title here</title>
</head>
<body>
 <div id="flip-tabs" >  
        <ul id="flip-navigation" >  
            <li class="selected"><a href="#" id="tab-0"  >Recent</a></li>  
            <li><a href="#" id="tab-1" >Popular</a></li>  
            <li><a href="#" id="tab-2" >Comments</a></li>  
        </ul>  
        <div id="flip-container" >  
            <div>  
                <!--Put Content for first tab here-->  
            </div>  
            <div>  
                <!--Put Content for second tab here-->  
            </div>  
            <div>  
                <!--Put Content for third tab here-->  
            </div>  
        </div>  
    </div> 
</body>
</html>