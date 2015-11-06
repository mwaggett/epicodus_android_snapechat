package io.github.mwaggett.snapechat.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.adapters.MessageAdapter;
import io.github.mwaggett.snapechat.models.Message;

public class ShowMessagesActivity extends ListActivity {

    private MessageAdapter mAdapter;
    private ArrayList<Message> mMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_messages);

        Message.all(new Runnable() {
            @Override
            public void run() {
                mMessages = (ArrayList) Message.getAllMessages();
                mAdapter = new MessageAdapter(ShowMessagesActivity.this, mMessages);
                setListAdapter(mAdapter);
            }
        });
    }

}
