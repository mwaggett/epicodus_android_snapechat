package io.github.mwaggett.snapechat.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Table(name = "Snapes", id="_id")
public class Snape implements Serializable {

    //@Column(name = "ImageSrc")
    private String mId;
    private int mImageSrc;
    private ParseObject mParseObject;
    private static List<Snape> mAllSnapes;

    private static Snape searchedSnape;


//    public Snape() {
//        super();
//    }

    public Snape(int imageSrc) {
        mImageSrc = imageSrc;

        mParseObject = new ParseObject("Snape");
        mParseObject.put("src", mImageSrc);
    }

    public Snape(ParseObject snape) {
        mId = snape.getObjectId();
        mImageSrc = snape.getInt("src");
        mParseObject = snape;
    }

    public void save() {
        mParseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mId = mParseObject.getObjectId();
                }
            }
        });
    }

    public int getImageSrc() {
        return mImageSrc;
    }

    public static void all(final Runnable runnable) {
        mAllSnapes = new ArrayList<Snape>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Snape");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject snape : objects) {
                        Snape newSnape = new Snape(snape);
                        mAllSnapes.add(newSnape);
                    }
                    runnable.run();
                }
            }
        });
    }

    public String getId() {
        return mId;
    }

    public ParseObject getParseObject() {
        return mParseObject;
    }

    public static List<Snape> getAllSnapes() {
        return mAllSnapes;
    }

    public static void find(String id, final Runnable runnable) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Snape");
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    searchedSnape = new Snape(object);
                    runnable.run();
                }
            }
        });
    }

    public static Snape getSearchedSnape() {
        return searchedSnape;
    }
}
