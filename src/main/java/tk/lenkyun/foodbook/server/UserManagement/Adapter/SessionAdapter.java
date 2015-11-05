package tk.lenkyun.foodbook.server.UserManagement.Adapter;

import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.SessionAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;

/**
 * Created by lenkyun on 5/11/2558.
 */
public interface SessionAdapter {
    SessionAuthenticationInfo createSession(User user, long timeout);
    void removeSessionByToken(SessionAuthenticationInfo sessionAuthenticationInfo);
    void removeSessionByUserId(String uid);
}
