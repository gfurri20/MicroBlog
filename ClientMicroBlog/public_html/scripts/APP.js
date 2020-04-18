

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
                        var post_template = "<div class='post' style='border: 2px solid black'>" +
                                            "<p><strong>" + post["title"] + "</strong></p>" +
                                            "<p>" + post["content"] + "</p>" +
                                            "<p>by " + post["author"]["username"] + "</p>" + 
                                            "<div class='comments_container" + post["id"] + "'>" +
                                            "</div>" +
                                            "</div>";
                        
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
                        var comment_template = "<hr><div class='comment'><p><strong>" + comment["author"]["username"] + "</strong></p><p>" + comment["content"] + "</p></div>";
                        
                        $(".comments_container" + post_id).append(comment_template);
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

