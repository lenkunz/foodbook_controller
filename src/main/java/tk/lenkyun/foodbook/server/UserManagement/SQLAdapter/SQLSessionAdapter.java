package tk.lenkyun.foodbook.server.UserManagement.SQLAdapter;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.SessionAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;
import tk.lenkyun.foodbook.server.UserManagement.Adapter.SessionAdapter;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Repository
public class SQLSessionAdapter extends SimpleJdbcDaoSupport implements SessionAdapter {
    @Override
    public SessionAuthenticationInfo createSession(User user, long timeout) {

        return null;
    }

    @Override
    public void removeSessionByToken(SessionAuthenticationInfo sessionAuthenticationInfo) {

    }

    @Override
    public void removeSessionByUserId(String uid) {

    }
}
