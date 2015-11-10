package io.github.mwaggett.snapechat.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.adapters.MessageAdapter;
import io.github.mwaggett.snapechat.models.Message;

public class ShowMessagesActivity extends ListActivity {

    private MessageAdapter mAdapter;
    private ArrayList<Message> mMessages;
    @Bind(R.id.viewThumbnailsButton) Button mViewThumbnailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_messages);
        ButterKnife.bind(this);

        Message.all(new Runnable() {
            @Override
            public void run() {
                mMessages = (ArrayList) Message.getAllMessages();
                mAdapter = new MessageAdapter(ShowMessagesActivity.this, mMessages);
                setListAdapter(mAdapter);
            }
        });

        mViewThumbnailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowMessagesActivity.this, ShowThumbnailsActivity.class);
                startActivity(intent);
            }
        });
    }

}
