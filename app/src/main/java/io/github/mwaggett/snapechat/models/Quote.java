package io.github.mwaggett.snapechat.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

//@Table(name = "Quotes", id = "_id")
public class Quote extends Model {

    //@Column(name = "Text")
    private String mText;

    public Quote() {
        super();
    }

    public Quote(String text) {
        super();
        mText = text;
    }

    public String getText() {
        return mText;
    }

//    public static List<Quote> all() {
//        return new Select().from(Quote.class).execute();
//    }
}
