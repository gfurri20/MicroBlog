
/**
 * Author:  gfurri20
 * Created: 7 mar 2020
 */

insert into users (username, email) values
    ('pippo', 'pippo@gmail.com');


insert into posts (title, content, pubblication_date, author_id) values
    ('first title', 'first content', '2020-03-07', 1);

insert into comments (content, pubblication_date, author_id, correlated_post_id) values
    ('first comment', '2020-03-14', 1, 1);

