
package it.gfurri20.blog.domain.auth;

import lombok.Data;


/**
 *
 * @author gfurri20
 */
@Data
public class RegistrationModelView
{
    private String username;
    private String password;
    private String repeatPassword;
}
