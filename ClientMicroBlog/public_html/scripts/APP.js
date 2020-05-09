

/*
 * @author gfurri20
 */
var APP =
{
    BASE_PATH: "http://localhost:8081/microblog/v2/api/",
    
    showPosts : function()
    {
        //gets all post from API
        $.ajax(
            {
                url: "http://localhost:8081/posts",
                method: "GET",
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
        var url = "http://localhost:8081/posts/" + post_id + "/comments";
        //gets all comments by post
        $.ajax(
            {
                url: url,
                method: "GET",
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
                success: function(post, status) {
                    APP.populate_post_template(post);
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
                success: function(comment, status) {
                    APP.populate_comment_template(comment);
                }
            }
        );
    },
    
    submitpost : function()
    {
        //gets variables from form
        var title = $("#input_post_title").val();
        var content = $("#input_post_content").val();
        
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
                            id: 1
                        }
                    }
                ),
                success: function(data , status, request) {
                    var address = request.getResponseHeader("Location");
                    APP.showPost(address);
                },
                statusCode: {
                    201: function() {
                        //todo something
                    }
                }
            }
        );
    },
    
    submitcomment : function(event)
    {
        //gets variables from form
        post_id = event.target.id;
        var comment_content = $("#content" + post_id).val();
        
        $.ajax(
            {
                url: "http://localhost:8081/comments",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                    {
                        content: comment_content,
                        correlatedPost: {
                            id: post_id
                        },
                        author: {
                            id: APP.COMMENT_AUTHOR_ID
                        }
                    }
                ),
                success: function(data, status, request) {
                    var address = request.getResponseHeader("Location");
                    APP.showComment(address);
                },
                statusCode: {
                    201: function() {
                        //todo
                    }
                }
            }    
        );
    },
    
    populate_post_template : function(post) {
        //clone template which will containes infos
        var post_template = $("#templates").find(".post_container_template").clone();

        //populate the template
        post_template.find(".post_title").append(post["title"]);
        post_template.find(".post_content").append(post["content"]);
        post_template.find(".post_author").append(post["author"]["username"]);
        post_template.find(".post_date").append(post["pubblicationDate"]);

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

        //populate the template
        comment_template.find(".comment_content").append(comment["content"]);
        comment_template.find(".comment_author").append(comment["author"]["username"]);

        //add to the template an id in order to identify each comments
        comment_template.attr("id", "comment" + comment["id"]);
        //append the populated template to the comments container
        $("#post" + post_id).find(".comments_container_template").append(comment_template);
    }
};

