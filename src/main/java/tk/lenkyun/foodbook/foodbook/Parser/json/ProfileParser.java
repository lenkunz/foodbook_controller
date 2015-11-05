package tk.lenkyun.foodbook.foodbook.Parser.json;

import org.json.JSONObject;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoItem;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.Profile;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class ProfileParser extends JSONParser<Profile> {
    public static final String PROFILE_PICTURE = "profile_pic",
                                COVER_PICTURE = "cover_pic",
                                FIRSTNAME = "firstname",
                                LASTNAME = "lastname";

    @Override
    public JSONObject parse(Profile object) {
        JSONObject json = new JSONObject();
        if(object.getProfilePicture() != null)
            json.put(PROFILE_PICTURE, new PhotoItemParser().parse(object.getProfilePicture()));

        if(object.getCoverPicture() != null)
            json.put(COVER_PICTURE, new PhotoItemParser().parse(object.getCoverPicture()));

        json.put(FIRSTNAME, object.getFirstname());
        json.put(LASTNAME, object.getLastname());

        return json;
    }

    @Override
    public Profile from(JSONObject json) {
        PhotoItem profile_pic = null,
                    cover_pic = null;

        if(json.has(PROFILE_PICTURE))
            profile_pic = new PhotoItemParser().from(json.getJSONObject(PROFILE_PICTURE));

        if(json.has(COVER_PICTURE))
            cover_pic = new PhotoItemParser().from(json.getJSONObject(COVER_PICTURE));

        Profile profile = new Profile(json.getString(FIRSTNAME), json.getString(LASTNAME), profile_pic);
        profile.setCoverPicture(cover_pic);

        return profile;
    }
}
