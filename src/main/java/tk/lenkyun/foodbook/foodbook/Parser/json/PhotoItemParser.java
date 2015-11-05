package tk.lenkyun.foodbook.foodbook.Parser.json;

import org.json.JSONObject;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoItem;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class PhotoItemParser extends JSONParser<PhotoItem> {
    public static final String REFERAL = "referal",
                                WIDTH = "width",
                                HEIGHT = "height";

    @Override
    public JSONObject parse(PhotoItem object) {
        JSONObject json = new JSONObject();
        json.put(REFERAL, object.getReferal().toString());
        json.put(WIDTH, object.getWidth());
        json.put(HEIGHT, object.getHeight());

        return json;
    }

    @Override
    public PhotoItem from(JSONObject json) {
        URI uri = null;
        if(json.has(REFERAL)){
            try {
                uri = new URI(json.getString(REFERAL));
            } catch (URISyntaxException e) {
                uri = null;
            }
        }
        return new PhotoItem(uri, json.getInt(WIDTH), json.getInt(HEIGHT));
    }
}
