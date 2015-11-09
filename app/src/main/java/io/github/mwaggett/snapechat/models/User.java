package io.github.mwaggett.snapechat.models;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

//@Table(name = "Users", id = "_id")
public class User {

    //@Column(name = "Name")
    private String mName;
    private String mPassword;
    private String mId;
    private ParseUser mParseUser;
    private static List<User> mAllUsers;

    private static User searchedUser;

//    public User() {
//        super();
//    }

    public User(String name) {
        mName = name;
        mPassword = "12345"; //obvi to be changed later

        mParseUser = new ParseUser();
        mParseUser.setUsername(mName);
        mParseUser.setPassword(mPassword);
    }

    public User(ParseUser user) {
        mName = user.getUsername();
        mId = user.getObjectId();
        mParseUser = user;
    }

    public void save() {
        mParseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mId = mParseUser.getObjectId();
                }
            }
        });
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getId() {
        return mId;
    }

    public ParseUser getParseUser() {
        return mParseUser;
    }

    public static void all(final Runnable runnable) {
        mAllUsers = new ArrayList<User>();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    for (ParseUser user : users) {
                        User existingUser = new User(user);
                        mAllUsers.add(existingUser);
//                        find(user.getObjectId(), new Runnable() {
//                            @Override
//                            public void run() {
//                                User existingUser = getSearchedUser();
//                                mAllUsers.add(existingUser);
//                            }
//                        });
                    }
                    runnable.run();
                }
            }
        });
    }

    public static List<User> getAllUsers() {
        return mAllUsers;
    }

    public static void find(String id, final Runnable runnable) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(id, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    searchedUser = new User(user);
                    runnable.run();
                }
            }
        });
    }

    public static User getSearchedUser() {
        return searchedUser;
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