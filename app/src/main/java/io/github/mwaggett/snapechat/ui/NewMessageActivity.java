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
    @Bind(R.id.sendButton) Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        ButterKnife.bind(this);

        mUser = new User("me");
        mUser.save();

        mContacts = (ArrayList) User.all();
        mContactsListView = (ListView) getListView();
        mAdapter = new ContactsAdapter(this, mContacts);
        setListAdapter(mAdapter);

        mSelectedSnape = (Snape) getIntent().getSerializableExtra("snape");
        mSnapeImage.setImageResource(mSelectedSnape.getImageSrc());
        mNewMessage = new Message(mSelectedSnape, mUser);

        mSelectContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContactsListView.setVisibility(View.VISIBLE);
                mSelectContactsButton.setVisibility(View.INVISIBLE);
            }
        });

        mContactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNewMessage.setReceiver(mContacts.get(position));
                mContactsListView.setVisibility(View.INVISIBLE);
                mSendButton.setText("Send To " + mNewMessage.getReceiver().getName());
                mSendButton.setVisibility(View.VISIBLE);
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewMessage.save();
                Intent intent = new Intent(NewMessageActivity.this, ShowMessagesActivity.class);
                startActivity(intent);
            }
        });
    }
}
