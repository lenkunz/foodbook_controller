package tk.lenkyun.foodbook.server.UserManagement.Utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;
import tk.lenkyun.foodbook.foodbook.Parser.json.UserParser;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Repository
public class TokenProvider {
    SecretKey secret;

    public TokenProvider(){
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            secret = generator.generateKey();
        } catch (NoSuchAlgorithmException ignored) {}
    }

    private JSONObject decodeToken(String token){
        byte[] bytes = Base64.decodeBase64(token);

        try {
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.DECRYPT_MODE, secret);

            byte[] decrypted = desCipher.doFinal(bytes);
            return new JSONObject(decrypted);
        }
        catch (NoSuchAlgorithmException ignored) {}
        catch (NoSuchPaddingException ignored) {}
        catch (InvalidKeyException e) {}
        catch (BadPaddingException e) {}
        catch (IllegalBlockSizeException e) {}

        return null;
    }

    public String getUserId(String token){
        JSONObject jsonObject = decodeToken(token);

        return new UserParser().from(jsonObject.getJSONObject("user")).getId();
    }

    public boolean isTokenTimeout(String token){
        JSONObject jsonObject = decodeToken(token);

        return (System.currentTimeMillis() / 1000) <= jsonObject.getLong("limit");
    }

    public String getToken(User user, long tokenLive){
        JSONObject json = new JSONObject();
        user.setAuthenticationInfo(null);
        user.setProfile(null);
        user.setSocialId(null);
        json.put("user", user);
        json.put("limit", (System.currentTimeMillis() / 1000) + tokenLive);

        UUID salt = UUID.randomUUID();
        json.put("salt", salt.toString());

        try {
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            byte[] bytes = json.toString().getBytes();
            desCipher.init(Cipher.ENCRYPT_MODE, secret);

            byte[] encrypted = desCipher.doFinal(bytes);
            return Base64.encodeBase64String(encrypted);
        }
        catch (NoSuchAlgorithmException ignored) {}
        catch (NoSuchPaddingException ignored) {}
        catch (InvalidKeyException e) {}
        catch (BadPaddingException e) {}
        catch (IllegalBlockSizeException e) {}

        return null;
    }
}
