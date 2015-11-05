package tk.lenkyun.foodbook.server.UserManagement.FacebookAdapter;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import tk.lenkyun.foodbook.server.UserManagement.Adapter.FacebookAdapter;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class SpringFacebookAdapter implements FacebookAdapter {
    @Override
    public String getFacebookIdFromToken(String accessToken){
        Facebook facebook = new FacebookTemplate(accessToken);
        return facebook.userOperations().getUserProfile().getId();
    }
}
