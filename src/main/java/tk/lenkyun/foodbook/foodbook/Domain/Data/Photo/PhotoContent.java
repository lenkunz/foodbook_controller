package tk.lenkyun.foodbook.foodbook.Domain.Data.Photo;

import tk.lenkyun.foodbook.foodbook.Domain.Data.Content;
import tk.lenkyun.foodbook.foodbook.Domain.Data.FoodbookType;

public class PhotoContent implements FoodbookType, Content<byte[]> {
    protected byte[] content;

    public PhotoContent(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        throw new UnsupportedOperationException("No support for PhotoContent to set its content.");
    }
}
