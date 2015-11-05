package tk.lenkyun.foodbook.server.UserManagement.Exception;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String s){
        super(s);
    }
}
