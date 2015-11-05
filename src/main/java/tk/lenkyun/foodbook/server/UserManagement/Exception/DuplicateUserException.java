package tk.lenkyun.foodbook.server.UserManagement.Exception;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(String string){
        super(string);
    }
}
