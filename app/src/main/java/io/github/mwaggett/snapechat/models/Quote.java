package io.github.mwaggett.snapechat.models;

//import com.activeandroid.Model;
//import com.activeandroid.annotation.Column;
//import com.activeandroid.annotation.Table;
//import com.activeandroid.query.Select;

//@Table(name = "Quotes", id = "_id")
public class Quote {

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

}
