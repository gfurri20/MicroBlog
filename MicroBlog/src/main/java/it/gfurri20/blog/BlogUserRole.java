package it.gfurri20.blog;



/**
 *
 * @author gfurri20
 */
public enum BlogUserRole
{
    Admin,
    Basic;   

    private BlogUserRole()
    {
    }

    public static BlogUserRole getAdmin()
    {
        return Admin;
    }

    public static BlogUserRole getBasic()
    {
        return Basic;
    }
    
}
