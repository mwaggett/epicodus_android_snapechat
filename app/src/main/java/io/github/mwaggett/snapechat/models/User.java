package io.github.mwaggett.snapechat.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Users", id = "_id")
public class User extends Model {

    @Column(name = "Name")
    private String mName;

    public User() {
        super();
    }

    public User(String name) {
        super();
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public static List<User> all() {
        return new Select().from(User.class).execute();
    }

    public List<Message> sentMessages() {
        return getMany(Message.class, "Sender");
    }

    public List<Message> receivedMessages() {
        return getMany(Message.class, "Receiver");
    }
}