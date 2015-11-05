package tk.lenkyun.foodbook.foodbook.Domain.Data.Photo;

import tk.lenkyun.foodbook.foodbook.Domain.Data.FoodbookType;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Referal;

import java.net.URI;

/**
 * Created by lenkyun on 15/10/2558.
 */
public class PhotoItem implements FoodbookType, Referal {
    public static final int UNKNOWN_WIDTH = -1, UNKNOWN_HEIGHT = -1;
    protected URI referalImageURI;
    protected int width, height;

    public PhotoItem(URI referalImageURI, int width, int height) {
        this.referalImageURI = referalImageURI;
        this.width = width;
        this.height = height;
    }

    @Override
    public URI getReferal() {
        return referalImageURI;
    }

    @Override
    public void setReferal(URI uri) {
        referalImageURI = uri;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
