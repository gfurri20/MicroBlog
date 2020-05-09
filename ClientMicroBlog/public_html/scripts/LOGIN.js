

/* global APP, USER */

/**
 * @author gfurri20
 */

var LOGIN =
{
    /**
     * Takes in input username and password and do the auth to the server, if it works store the jwt token
     * 
     * @returns void
     */
    doLogin : function()
    {
        var input_username = $("#login_username").val();
        var input_pwd = $("#login_password").val();
        
        $.ajax(
            {
                url: APP.BASE_PATH + "login",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                        {
                            username: input_username,
                            password: input_pwd
                        }),
                success: function(data, status, request) {
                   USER.JWT_TOKEN = request.getResponseHeader('Authorization').split(" ").pop();
                   USER.USERNAME = input_username;
                }
            } 
        );
    }
};
