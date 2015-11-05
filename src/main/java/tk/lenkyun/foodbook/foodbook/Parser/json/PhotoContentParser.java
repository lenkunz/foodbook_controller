package tk.lenkyun.foodbook.foodbook.Parser.json;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoContent;

/**
 * Created by lenkyun on 4/11/2558.
 */
public class PhotoContentParser extends JSONParser<PhotoContent> {
    @Override
    public JSONObject parse(PhotoContent object) {
        JSONObject json = new JSONObject();

        try {
            json.put("content", parse(object.getContent()));
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public PhotoContent from(JSONObject json) {
        try {
            return new PhotoContent(decodeBitmap(json.getString("content")));
        } catch (JSONException e) {
            return null;
        }
    }

    public byte[] decodeBitmap(String base64){
        byte[] bytes = Base64.decodeBase64(base64);
        return bytes;
    }

    public String parse(byte[] bitmap){
        return Base64.encodeBase64String(bitmap);
    }
}
