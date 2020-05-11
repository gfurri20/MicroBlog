
package it.gfurri20.blog.security.jwt;


/**
 *
 * @author gfurri20
 */
public class JwtProperties
{
    public static final String SECRET = "microblog";
    public static final int EXPIRATION_TIME = 840000000; // 1 ora
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
