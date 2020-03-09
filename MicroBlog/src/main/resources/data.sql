/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  gfurri20
 * Created: 7 mar 2020
 */
insert into users (username, password, salt) values
    ('admin', 'admin', 'salt');
commit;

insert into posts (title, content, pubblication_date, author_id) values
    ('first title', 'first content', '07/03/2020', 1);
commit;
