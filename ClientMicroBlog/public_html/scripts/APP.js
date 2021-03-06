

/*
 * @author gfurri20
 */

var APP =
{
    BASE_PATH: "http://localhost:8081/microblog/v2/api/",
    
    HEADER_STRING: "Authorization",
    TOKEN_PREFIX: "Bearer ",
    
    showPosts : function()
    {
        //gets all post from API
        $.ajax(
            {
                url: APP.BASE_PATH + "posts",
                method: "GET",
                beforeSend: function(xhr){
                    if( sessionStorage.getItem("JWT_TOKEN") !== null )
                    {
                        xhr.setRequestHeader(APP.HEADER_STRING, APP.TOKEN_PREFIX + sessionStorage.getItem("JWT_TOKEN"));
                    }                    
                },
                success: function(data, status) {
                    
                    $.each(data, function(index, post) {
                        APP.populate_post_template(post);
                    });
                    
                }
            } 
        );
    },
    
    showCommentsByPost : function(post_id)
    {
        var url = APP.BASE_PATH + "posts/" + post_id + "/comments";
        //gets all comments by post
        $.ajax(
            {
                url: url,
                method: "GET",
                beforeSend: function(xhr){
                    if( sessionStorage.getItem("JWT_TOKEN") !== null )
                    {
                        xhr.setRequestHeader(APP.HEADER_STRING, APP.TOKEN_PREFIX + sessionStorage.getItem("JWT_TOKEN"));
                    }                    
                },
                success: function(data, status) {
                    if(data.length !== 0)
                    {                       
                        $.each(data, function(index, comment)
                        {
                            APP.populate_comment_template(comment);
                        });
                    }
                }
            }
        );
    },
    
    showPost : function(address) {
        $.ajax(
            {
                url: address,
                method: "GET",
                beforeSend: function(xhr){
                    if( sessionStorage.getItem("JWT_TOKEN") !== null )
                    {
                        xhr.setRequestHeader(APP.HEADER_STRING, APP.TOKEN_PREFIX + sessionStorage.getItem("JWT_TOKEN"));
                    }                    
                },
                success: function(post, status) {
                    APP.populate_post_template(post);
                },
                statusCode: {
                    404: function() {
                        alert("Comment not found!");
                    }
                }
            }
        );
    },
    
    /**
     * Send a <code>HTTP GET Request</code> to get a specific comment by its id
     * 
     * @param {string} address
     * @returns {void}
     */
    showComment : function(address) {       
        $.ajax(
            {
                url: address,
                method: "GET",
                beforeSend: function(xhr){
                    if( sessionStorage.getItem("JWT_TOKEN") !== null )
                    {
                        xhr.setRequestHeader(APP.HEADER_STRING, APP.TOKEN_PREFIX + sessionStorage.getItem("JWT_TOKEN"));
                    }                    
                },
                success: function(comment, status) {
                    APP.populate_comment_template(comment);
                },
                statusCode: {
                    404: function() {
                        alert("Comment not found!");
                    }
                }
            }
        );
    },
    
    submitpost : function()
    {
        //gets variables from form
        var title = $("#input_post_title").val();
        var content = $("#input_post_content").val();
        
        //check if there is something
        if(title.length === 0 || content.length === 0)
        {
            alert("Write something in the post!");
            return null;
        }
        
        $.ajax(
            {
                url: APP.BASE_PATH + "posts",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                    {
                        title: title,
                        content: content,
                        author: {
                            username: sessionStorage.getItem("CURRENT_USERNAME")
                        }
                    }
                ),
                beforeSend: function(xhr){
                    if( sessionStorage.getItem("JWT_TOKEN") !== null )
                    {
                        xhr.setRequestHeader(APP.HEADER_STRING, APP.TOKEN_PREFIX + sessionStorage.getItem("JWT_TOKEN"));
                    }                    
                },
                success: function(data , status, request) {                   
                    var address = request.getResponseHeader("Location");
                    APP.showPost(address);
                },
                statusCode: {
                    403: function() {
                        alert("You have to be an ADMIN to post something");
                    }
                }
            }
        );
    },
    
    submitcomment : function(event)
    {
        //gets variables from the form
        post_id = event.target.id;
        var comment_content = $("#content" + post_id).val();
        
        //check if there is something
        if(comment_content.length === 0)
        {
            alert("Write something in the comment!");
            return null;
        }
        
        $.ajax(
            {
                url: APP.BASE_PATH + "comments",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                    {
                        content: comment_content,
                        correlatedPost: {
                            id: post_id
                        },
                        author: {
                            username: sessionStorage.getItem("CURRENT_USERNAME")
                        }
                    }
                ),
                beforeSend: function(xhr){
                    if( sessionStorage.getItem("JWT_TOKEN") !== null )
                    {
                        xhr.setRequestHeader(APP.HEADER_STRING, APP.TOKEN_PREFIX + sessionStorage.getItem("JWT_TOKEN"));
                    }                    
                },
                success: function(data, status, request) {                    
                    var address = request.getResponseHeader("Location");
                    APP.showComment(address);
                },
                statusCode: {
                    403: function() {
                        alert("You have to be logged in to comment");
                    }
                }
            }    
        );
    },
    
    /**
     * Takes in input username and password and do the auth to the server, if it works store the jwt token
     * 
     * @returns void
     */
    doLogin : function()
    {
        var input_username = $("#login_username").val();
        var input_pwd = $("#login_password").val();
        
        //check the inpit values
        let re = /[\s]/;
        var resUsername = re.test(input_username);
        var resPassword = re.test(input_pwd);
        
        //if there are empty values
        if(input_username.length === 0 || input_pwd.length === 0)
        {
            alert("No empty value!");
            return null;
        }
        //if there are spaces
        if(resUsername || resPassword)
        {
            alert("No spaces!");
            return null;
        }
        
        
        $.ajax(
            {
                url: "http://localhost:8081/microblog/v2/login",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                        {
                            username: input_username,
                            password: input_pwd
                        }),
                success: function(data, status, request) {
                   sessionStorage.setItem("JWT_TOKEN", request.getResponseHeader('Authorization').split(" ").pop());
                   sessionStorage.setItem("CURRENT_USERNAME", input_username);
                   window.location.assign("index.html");
                },
                statusCode: {
                    401: function() {                       
                        alert("Wrong username or password!");
                    },
                    422: function() {                       
                        alert("Invalid request!");
                    }
                }
            } 
        );
    },
    
    /**
     * Takes in input username and password and do the auth to the server, if it works store the jwt token
     * 
     * @returns void
     */
    doRegistration : function()
    {
        var input_username = $("#registration_username").val();
        var input_pwd = $("#registration_password").val();
        var input_repeat_pwd = $("#registration_repeat_password").val();
        
        //check the inpit values
        let re = /[\s]/;
        var resUsername = re.test(input_username);
        var resPassword = re.test(input_pwd);
        var resRepeatPassword = re.test(input_repeat_pwd);
        
        //if there are empty values
        if(input_username.length === 0 || input_pwd.length === 0 || input_repeat_pwd.length === 0)
        {
            alert("No empty value!");
            return null;
        }
        //if there are spaces
        if(resUsername || resPassword || resRepeatPassword)
        {
            alert("No spaces!");
            return null;
        }
        //if the two pwds are equal
        if(input_pwd !== input_repeat_pwd)
        {
            alert("Repeated password does not correspond!");
            return null;
        }
        
        $.ajax(
            {
                url: "http://localhost:8081/microblog/v2/registration",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                        {
                            username: input_username,
                            password: input_pwd,
                            repeatPassword: input_repeat_pwd
                        }),
                success: function(data, status, request) {
                   sessionStorage.setItem("JWT_TOKEN", request.getResponseHeader('Authorization').split(" ").pop());
                   sessionStorage.setItem("CURRENT_USERNAME", input_username);
                   window.location.assign("index.html");
                },
                statusCode: {
                    401: function() {                        
                        alert("Authentication failed!");
                    },
                    422: function() {                       
                        alert("Username already used!");
                    }
                }
            } 
        );
    },
    
    doLogout : function()
    {
        if( sessionStorage.getItem("JWT_TOKEN") !== null )
        {
            //deletes the variables correlated to an user
            sessionStorage.removeItem("JWT_TOKEN");
            sessionStorage.removeItem("CURRENT_USERNAME");
            //updates the status banner
            $("#log_status_banner").html("Not Logged");
            $("#log_status_banner").css("color", "red");
            //alert the browser
            alert("Logout successfully!");
        }
    },
    
    populate_post_template : function(post) {
        //clone template which will containes infos
        var post_template = $("#templates").find(".post_container_template").clone();
        
        //formatting date
        var date = new Date(post["pubblicationDate"]);
        year = date.getFullYear();
        month = date.getMonth()+1;
        dt = date.getDate();

        if (dt < 10) {
          dt = '0' + dt;
        }
        if (month < 10) {
          month = '0' + month;
        }
        
        var simple_date = year+'-' + month + '-'+dt;

        //populate the template
        post_template.find(".post_title").append(post["title"]);
        post_template.find(".post_content").append(post["content"]);
        post_template.find(".post_author").append(post["author"]["username"]);
        post_template.find(".post_date").append(simple_date);

        //add to the template an id in order to identify each posts
        post_template.attr("id", "post" + post["id"]);
        //append the populated template to the posts container
        $("#posts_container").append(post_template);

        //create and append comment form
        var comment_form_template = $("#templates").find(".submit_comment_form_template").clone();
        comment_form_template.find(".btn_input_comment_submit").attr("id", post["id"]);
        comment_form_template.find(".input_comment_content").attr("id", "content" + post["id"]);
        post_template.append(comment_form_template);
        
        //comments container
        var comments_template = $("#templates").find(".comments_container_template").clone();
        post_template.append(comments_template);

        //callback for submit comment
        $("#" + post["id"]).on("click", APP.submitcomment);

        APP.showCommentsByPost(post['id']);
    },
    
    populate_comment_template : function(comment) {
        //id of the correlated post
        var post_id = comment["correlatedPost"]["id"];      
        
        //clone template which will containes infos
        var comment_template = $("#templates").find(".comment_container_template").clone();
        
        //formatting date
        var date = new Date(comment["pubblicationDate"]);
        year = date.getFullYear();
        month = date.getMonth()+1;
        dt = date.getDate();

        if (dt < 10) {
          dt = '0' + dt;
        }
        if (month < 10) {
          month = '0' + month;
        }
        
        var simple_date = year+'-' + month + '-'+dt;

        //populate the template
        comment_template.find(".comment_content").append(comment["content"]);
        comment_template.find(".comment_author").append(comment["author"]["username"]);

        //add to the template an id in order to identify each comments
        comment_template.attr("id", "comment" + comment["id"]);
        //append the populated template to the comments container
        $("#post" + post_id).find(".comments_container_template").append(comment_template);
    }
};

