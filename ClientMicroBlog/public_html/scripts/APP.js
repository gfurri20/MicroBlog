

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
                    $.each(data, function(index, comment) {
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
        );
    },
    
    submitpost : function()
    {
        //gets variables from form
        var title = $("#post_title").val();
        var content = $("#post_content").val();
        
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
                            id: APP.AUTHOR_ID
                        }
                    }
                )
            }
        );
    },
    
    submitcomment : function()
    {
        //gets variables from form
        var comment_content = $("#comment_content").val();
        //take the post from an hidden html element
        var post_id = $("#post_id").val();
        
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
                            id: APP.AUTHOR_ID
                        }
                    }
                )
            }    
        );
    }
};

