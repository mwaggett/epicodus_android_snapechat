package io.github.mwaggett.snapechat.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//@Table(name = "Users", id = "_id")
public class User {

    //@Column(name = "Name")
    private String mName;
    private ParseUser mParseUser;
    private static List<User> mAllUsers;

//    public User() {
//        super();
//    }

    public User(String name) {
        mName = name;

        mParseUser = new ParseUser();
        mParseUser.setUsername(mName);
    }

    public User(ParseUser user) {
        mName = user.getUsername();
        mParseUser = user;
    }

    public void save() {
        mParseUser.signUpInBackground();
    }

    public String getName() {
        return mName;
    }

    public static void all(final Runnable runnable) {
        mAllUsers = new ArrayList<User>();
        ParseQuery<ParseUser> query = ParseQuery.getQuery("User");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    for (ParseUser user : users) {
                        User newUser = new User(user);
                        mAllUsers.add(newUser);
                    }
                    runnable.run();
                }
            }
        });
    }

    public static List<User> getAllUsers() {
        return mAllUsers;
    }
//
//    public List<Message> sentMessages() {
//        return getMany(Message.class, "Sender");
//    }
//
//    public List<Message> receivedMessages() {
//        return getMany(Message.class, "Receiver");
//    }
}