package tk.lenkyun.foodbook.server.PhotoManagement.Adapter;

import org.springframework.stereotype.Repository;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoContent;

import java.net.URI;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Repository
public class LocalPhotoAdapter implements PhotoAdapter {
    @Override
    public URI postPhoto(PhotoContent photoContent) {
        return null;
    }

    @Override
    public void getPhoto(URI uri) {

    }

    @Override
    public void removePhoto(URI uri) {

    }
}
