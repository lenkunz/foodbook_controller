package tk.lenkyun.foodbook.server.UserManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.SessionAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.UserAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;
import tk.lenkyun.foodbook.foodbook.Domain.Operation.RegistrationHelper;
import tk.lenkyun.foodbook.server.PhotoManagement.Adapter.PhotoAdapter;
import tk.lenkyun.foodbook.server.Config;
import tk.lenkyun.foodbook.server.UserManagement.Adapter.SessionAdapter;
import tk.lenkyun.foodbook.server.UserManagement.Adapter.UserAdapter;
import tk.lenkyun.foodbook.server.UserManagement.Exception.DuplicateUserException;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Service
public class UserAuthentication {
    @Autowired
    private UserAdapter userAdapter;
    @Autowired
    private SessionAdapter sessionAdapter;
    @Autowired
    private PhotoAdapter photoAdapter;

    public SessionAuthenticationInfo login(UserAuthenticationInfo authenticationInfo){
        User user = userAdapter.getUser(authenticationInfo);
        if(user == null)
            return null;

        SessionAuthenticationInfo session = sessionAdapter.createSession(user, Config.SESSION_SHORT);
        return session;
    }

    SessionAuthenticationInfo login(User user){
        return sessionAdapter.createSession(user, Config.SESSION_SHORT);
    }

    public User register(RegistrationHelper helper) throws DuplicateUserException {
        if(userAdapter.getUserByUsername(helper.getUsername()) == null){
            return userAdapter.createUser(helper, null, null);
        }else{
            throw new DuplicateUserException(helper.getUsername());
        }
    }
}
