package io.github.mwaggett.snapechat.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

@Table(name = "Snapes", id="_id")
public class Snape extends Model implements Serializable {

    @Column(name = "ImageSrc")
    private int mImageSrc;

    public Snape() {
        super();
    }

    public Snape(int imageSrc) {
        mImageSrc = imageSrc;
    }

    public int getImageSrc() {
        return mImageSrc;
    }

    public static List<Snape> all() {
        return new Select().from(Snape.class).execute();
    }
}
