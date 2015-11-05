package tk.lenkyun.foodbook.server.PhotoManagement.Adapter;

import org.springframework.stereotype.Repository;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoContent;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Repository
public class LocalPhotoAdapter implements PhotoAdapter {
    @Override
    public URI postPhoto(PhotoContent photoContent) {
        UUID uuid = UUID.randomUUID();
        File dir = new File("./dir");
        if(!dir.exists()){
            dir.mkdir();
        }

        File file = new File("./img/" + uuid.toString() + ".jpg");
        while(file.exists()){
            uuid = UUID.randomUUID();
            file = new File("./img/" + uuid.toString() + ".jpg");
        };

        try {
            FileOutputStream writer = new FileOutputStream("./img/" + uuid.toString() + ".jpg");
            writer.write(photoContent.getContent());
            writer.flush();
            writer.close();

            return new URI("http://192.168.56.1/img/" + uuid.toString() + ".jpg");
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        catch (URISyntaxException e) {}
        return null;
    }

    @Override
    public void getPhoto(URI uri) {

    }

    @Override
    public void removePhoto(URI uri) {

    }
}
