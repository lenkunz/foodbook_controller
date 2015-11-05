package tk.lenkyun.foodbook.server.PhotoManagement.Adapter;

import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoContent;

import java.net.URI;

/**
 * Created by lenkyun on 5/11/2558.
 */
public interface PhotoAdapter {
    URI postPhoto(PhotoContent photoContent);
    void getPhoto(URI uri);
    void removePhoto(URI uri);
}
