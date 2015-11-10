package io.github.mwaggett.snapechat.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import com.parse.ParseUser;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.adapters.ImageAdapter;
import io.github.mwaggett.snapechat.models.Snape;

public class ShowThumbnailsActivity extends AppCompatActivity {

    ArrayList<Snape> mSnapes;
    ImageAdapter mAdapter;
    @Bind(R.id.gridview) GridView gridview;
    @Bind(R.id.viewMessagesButton) Button mViewMessagesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_thumbnails);
        ButterKnife.bind(this);

        if (ParseUser.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        Snape.all(new Runnable() {
            @Override
            public void run() {
                mSnapes = (ArrayList<Snape>) Snape.getAllSnapes();
                mAdapter = new ImageAdapter(ShowThumbnailsActivity.this, mSnapes);
                gridview.setAdapter(mAdapter);
            }
        });

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(ShowThumbnailsActivity.this, ShowPhotosActivity.class);
                intent.putExtra("selected_snape", position);
                startActivity(intent);
            }
        });

        mViewMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowThumbnailsActivity.this, ShowMessagesActivity.class);
                startActivity(intent);
            }
        });
    }
}
