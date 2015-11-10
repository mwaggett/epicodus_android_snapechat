package io.github.mwaggett.snapechat.models;

//import com.activeandroid.Model;
//import com.activeandroid.annotation.Column;
//import com.activeandroid.annotation.Table;
//import com.activeandroid.query.Select;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Table(name = "Messages", id = "_id")
public class Message {

    //@Column(name = "Snape")
    private Snape mSnape;

    //@Column(name = "Quote")
    //private Quote mQuote;

    //@Column(name = "CreatedAt")
    private Date mCreatedAt;

    //@Column(name = "Sender")
    private User mSender;

    //@Column(name = "Receiver")
    private User mReceiver;

    private ParseObject mParseObject;
    private static List<Message> mAllMessages;

//    public Message() {
//        super();
//    }

    public Message(Snape snape, User sender) {
//        super();
        mSnape = snape;
        mSender = sender;

        mParseObject = new ParseObject("Message");
        mParseObject.put("snape", mSnape.getParseObject());
        mParseObject.put("sender", mSender.getParseUser());
    }

    public Message(ParseObject message) {
        mSnape = new Snape(message.getParseObject("snape"));
        //mQuote = new Quote(message.getString("quote"));
        mCreatedAt = message.getCreatedAt();
        mSender = new User(message.getParseUser("sender"));
        mReceiver = new User(message.getParseUser("receiver"));
    }

    public void save(final Runnable runnable) {
        mParseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mCreatedAt = mParseObject.getCreatedAt();
                    runnable.run();
                }
            }
        });
    }

    public Snape getSnape() {
        return mSnape;
    }

    public void setSnape(Snape snape) {
        mSnape = snape;
    }

//    public Quote getQuote() {
//        return mQuote;
//    }

//    public void setQuote(Quote quote) {
//        mQuote = quote;
//        //mParseObject.put("quote", mQuote.getParseObject());
//    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public String getFormattedCreatedAt() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
        return formatter.format(mCreatedAt);
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public User getSender() {
        return mSender;
    }

    public void setSender(User sender) {
        mSender = sender;
        mParseObject.put("sender", mSender.getParseUser());
    }

    public User getReceiver() {
        return mReceiver;
    }

    public void setReceiver(User receiver) {
        mReceiver = receiver;
        mParseObject.put("receiver", mReceiver.getParseUser());
    }

    public ParseObject getParseObject() {
        return mParseObject;
    }

    public static void all(final Runnable runnable) {
        mAllMessages = new ArrayList<Message>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        Message newMessage = new Message(object);
                        mAllMessages.add(newMessage);
                    }
                    runnable.run();
                }
            }
        });
    }

    public static List<Message> getAllMessages() {
        return mAllMessages;
    }
}
