package io.github.mwaggett.snapechat.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name = "Messages", id = "_id")
public class Message extends Model {

    @Column(name = "Snape")
    private Snape mSnape;

    @Column(name = "Quote")
    private Quote mQuote;

    @Column(name = "CreatedAt")
    private long mCreatedAt;

    @Column(name = "Sender")
    private User mSender;

    @Column(name = "Receiver")
    private User mReceiver;

    public Message() {
        super();
    }

    public Message(Snape snape, User sender) {
        super();
        mSnape = snape;
        mSender = sender;
        mCreatedAt = new Date().getTime();
    }

    public Message(Snape snape, Quote quote, User sender, User receiver) {
        super();
        mSnape = snape;
        mQuote = quote;
        mCreatedAt = new Date().getTime();
        mSender = sender;
        mReceiver = receiver;
    }

    public Snape getSnape() {
        return mSnape;
    }

    public void setSnape(Snape snape) {
        mSnape = snape;
    }

    public Quote getQuote() {
        return mQuote;
    }

    public void setQuote(Quote quote) {
        mQuote = quote;
    }

    public long getCreatedAt() {
        return mCreatedAt;
    }

    public String getFormattedCreatedAt() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
        return formatter.format(mCreatedAt);
    }

    public void setCreatedAt(long createdAt) {
        mCreatedAt = createdAt;
    }

    public User getSender() {
        return mSender;
    }

    public void setSender(User sender) {
        mSender = sender;
    }

    public User getReceiver() {
        return mReceiver;
    }

    public void setReceiver(User receiver) {
        mReceiver = receiver;
    }

    public static List<Message> all() {
        return new Select().from(Message.class).execute();
    }
}
