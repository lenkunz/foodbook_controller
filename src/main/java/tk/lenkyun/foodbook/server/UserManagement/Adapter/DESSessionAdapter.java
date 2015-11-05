package tk.lenkyun.foodbook.server.UserManagement.Adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.SessionAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;
import tk.lenkyun.foodbook.server.UserManagement.Utils.TokenProvider;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Repository
public class DESSessionAdapter implements SessionAdapter {
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public SessionAuthenticationInfo createSession(User user, long timeout) {
        return new SessionAuthenticationInfo(user.getId(), user.getUsername(),
                tokenProvider.getToken(user, timeout));
    }

    @Override
    public void removeSessionByToken(SessionAuthenticationInfo sessionAuthenticationInfo) {
        return;
    }

    @Override
    public void removeSessionByUserId(String uid) {
        return;
    }
}
