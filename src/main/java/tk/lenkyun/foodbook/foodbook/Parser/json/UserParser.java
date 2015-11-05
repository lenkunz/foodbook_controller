package tk.lenkyun.foodbook.foodbook.Parser.json;

import org.json.JSONObject;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.AuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.UserAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.Profile;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;
import tk.lenkyun.foodbook.server.UserManagement.UserAuthentication;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class UserParser extends JSONParser<User>{
    public static final String ID = "id",
                                USERNAME = "username",
                                PROFILE = "profile",
                                FACEBOOKID = "social_id",
                                USERAUTHEN = "authen";

    @Override
    public JSONObject parse(User object) {
        JSONObject json = new JSONObject();

        json.put(ID, object.getId());
        json.put(USERNAME, object.getUsername());

        if(object.getProfile() != null)
            json.put(PROFILE, new ProfileParser().parse(object.getProfile()));

        if(object.getSocialId() != null && object.getSocialId().length() > 0)
            json.put(FACEBOOKID, object.getSocialId());

        if(object.getAuthenticationInfo() != null)
            json.put(USERAUTHEN, new AuthenticationInfoParser().parse(object.getAuthenticationInfo()));

        return json;
    }

    @Override
    public User from(JSONObject json) {
        AuthenticationInfo authenticationInfo = null;
        if(json.has(USERAUTHEN))
            authenticationInfo = new AuthenticationInfoParser().from(json.getJSONObject(USERAUTHEN));

        Profile profile = null;
        if(json.has(PROFILE))
            profile = new ProfileParser().from(json.getJSONObject(PROFILE));

        User user = new User(json.getString(ID), json.getString(USERNAME), profile);

        if(authenticationInfo.getInfo().equals(UserAuthenticationInfo.AUTH_TYPE))
            user.setAuthenticationInfo(
                    new UserAuthenticationInfo(authenticationInfo.getId(), authenticationInfo.getInfo())
            );

        if(json.has(FACEBOOKID) && json.getString(FACEBOOKID).length() > 0)
            user.setSocialId(json.getString(FACEBOOKID));

        return user;
    }
}
