package tk.lenkyun.foodbook.foodbook.Parser.rowset;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.UserAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoItem;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.Profile;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class UserParser extends RowsetParser<User> {
    public static final String UID = "uid",
                                USERNAME = "username",
                                PASSWORD = "password",
                                SOCIALID = "socialid",
                                FIRSTNAME = "firstname",
                                LASTNAME = "lastname",
                                PROFILE_IMAGE = "profile_image";


    @Override
    public Map<String, Object> parse(User object) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put(UID, Long.parseLong(object.getId()));
        hashMap.put(USERNAME, object.getUsername());
        hashMap.put(PASSWORD, object.getAuthenticationInfo().getInfo());
        hashMap.put(SOCIALID, object.getSocialId());
        hashMap.put(FIRSTNAME, object.getProfile().getFirstname());
        hashMap.put(LASTNAME, object.getProfile().getLastname());
        hashMap.put(PROFILE_IMAGE, object.getProfile().getProfilePicture().getReferal().toString());

        return hashMap;
    }

    @Override
    public User from(SqlRowSet rowSet) {
        URI imageURI = null;
        if(rowSet.getString(PROFILE_IMAGE) != null){
            try {
                imageURI = new URI(rowSet.getString(PROFILE_IMAGE));
            } catch (URISyntaxException ignored) {}
        }

        PhotoItem photoItem = new PhotoItem(imageURI, 0, 0);
        Profile profile = new Profile(rowSet.getString(FIRSTNAME),
                rowSet.getString(LASTNAME),
                photoItem);

        User user = new User(String.valueOf(rowSet.getLong(UID)), rowSet.getString(USERNAME), profile);
        user.setAuthenticationInfo(
                new UserAuthenticationInfo(user.getId(), rowSet.getString(PASSWORD))
        );

        return user;
    }
}
