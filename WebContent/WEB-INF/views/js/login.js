function onSubmit(){

        var uname=document.getElementById("username").value;
        var pass=document.getElementById("password").value;
        var flag=false;
        
        if ( IsEmpty(uname)==true ) {    
            document.getElementById("error").innerHTML = "* Please Enter Expiry Date";
       flag = true ;
        }
        
        if (IsEmpty(pass)==true  ) {    
            document.getElementById("error").innerHTML = "* Please Enter Start Date";
            flag = true;
        }
        
        if(flag)
        {
            return false;
        }
        return true;
    }

    function IsEmpty(input)
    {
       if(input.replace(/^\s+|\s+$/g,"")    === "") {
           return true;
       }
      return false;
    }