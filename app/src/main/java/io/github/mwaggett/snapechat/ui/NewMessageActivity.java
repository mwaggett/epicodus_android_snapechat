package io.github.mwaggett.snapechat.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.adapters.ContactsAdapter;
import io.github.mwaggett.snapechat.models.Message;
import io.github.mwaggett.snapechat.models.Snape;
import io.github.mwaggett.snapechat.models.User;

public class NewMessageActivity extends ListActivity {

    public static final String TAG = NewMessageActivity.class.getSimpleName();

    private User mUser;
    private Message mNewMessage;
    private Snape mSelectedSnape;
    private ArrayList<User> mContacts;
    private ListView mContactsListView;
    private ContactsAdapter mAdapter;
    @Bind(R.id.snapeImage) ImageView mSnapeImage;
    @Bind(R.id.selectContactsButton) Button mSelectContactsButton;
    @Bind(R.id.sendToBanner) TextView mSendToBanner;
    @Bind(R.id.sendButton) Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        ButterKnife.bind(this);
        mContactsListView = (ListView) getListView();

        //mUser = new User(ParseUser.getCurrentUser());

        String selectedSnapeId = getIntent().getStringExtra("snapeId");
        Snape.find(selectedSnapeId, new Runnable() {
            @Override
            public void run() {
                User.find("mVtXrrg6v8", new Runnable() {
                    @Override
                    public void run() {
                        mUser = User.getSearchedUser();
                        mSelectedSnape = Snape.getSearchedSnape();
                        mSnapeImage.setImageResource(mSelectedSnape.getImageSrc());
                        mNewMessage = new Message(mSelectedSnape, mUser);
                    }
                });
            }
        });

        User.all(new Runnable() {
            @Override
            public void run() {
                mContacts = (ArrayList<User>) User.getAllUsers();
                mAdapter = new ContactsAdapter(NewMessageActivity.this, mContacts);
                setListAdapter(mAdapter);
            }
        });

        mSelectContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendToBanner.setVisibility(View.VISIBLE);
                mContactsListView.setVisibility(View.VISIBLE);
                mSelectContactsButton.setVisibility(View.INVISIBLE);
            }
        });

        mContactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNewMessage.setReceiver(mContacts.get(position));
                mSendToBanner.setVisibility(View.INVISIBLE);
                mContactsListView.setVisibility(View.INVISIBLE);
                mSendButton.setText("Send To " + mNewMessage.getReceiver().getName());
                mSendButton.setVisibility(View.VISIBLE);
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewMessage.save(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(NewMessageActivity.this, "Sent to " + mNewMessage.getReceiver().getName() + "!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewMessageActivity.this, ShowMessagesActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
