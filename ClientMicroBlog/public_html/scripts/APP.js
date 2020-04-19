

/*
 * @author gfurri20
 */
var APP =
{
    //mockup user
    POST_AUTHOR_ID : 1,
    COMMENT_AUTHOR_ID: 2,
    
    showPosts : function()
    {
        //gets all post from API
        $.ajax(
            {
                url: "http://localhost:8081/posts",
                method: "GET",
                success: function(data, status) {
                    $.each(data, function(index, post) {
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
                        
                        //callback for submit comment
                        $("#" + post["id"]).on("click", APP.submitcomment);
                        
                        APP.showCommentsByPost(post['id']);
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
                        //if there are comments create the container which will contain all of them
                        var comments_template = $("#templates").find(".comments_container_template").clone();
                        $("#post" + post_id).append(comments_template);
                        
                        $.each(data, function(index, comment)
                        {
                            //clone template which will containes infos
                            var comment_template = $("#templates").find(".comment_container_template").clone();

                            //populate the template
                            comment_template.find(".comment_content").append(comment["content"]);
                            comment_template.find(".comment_author").append(comment["author"]["username"]);

                            //add to the template an id in order to identify each comments
                            comment_template.attr("id", "comment" + comment["id"]);
                            //append the populated template to the comments container
                            $("#post" + post_id).find(".comments_container_template").append(comment_template);                        
                        });
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
        
        $.ajax(
            {
                url: "http://localhost:8081/posts",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(
                    {
                        title: title,
                        content: content,
                        author: {
                            id: APP.POST_AUTHOR_ID
                        }
                    }
                ),
                statusCode: {
                    201: function() {
                        alert("Post successfully created");
                        window.location.reload();
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
                statusCode: {
                    201: function() {
                        alert("Comment successfully created");
                        window.location.reload();
                    }
                }
            }    
        );

        
    }
};

