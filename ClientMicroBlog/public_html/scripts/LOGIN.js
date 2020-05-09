

/* global APP, USER */

/**
 * @author gfurri20
 */

var LOGIN =
{
    doLogin : function()
    {        
        $.ajax(
            {
                url: APP.BASE_PATH + "login",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                        {
                            username: "pippo",
                            password: "pippo"
                        }),
                success: function(data, status, request) {
                    var complete_token = request.getResponseHeader('Authorization');
                    
                    USER.USERNAME = "pippo";
                    
                    alert(complete_token);
                }
            } 
        );
    }
};
