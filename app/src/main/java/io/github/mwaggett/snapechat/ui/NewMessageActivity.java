package io.github.mwaggett.snapechat.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.models.Snape;

public class NewMessageActivity extends AppCompatActivity {

    private Snape mSelectedSnape;
    private ImageView mSnapeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        mSelectedSnape = (Snape) getIntent().getSerializableExtra("snape");
        mSnapeImage = (ImageView) findViewById(R.id.snapeImage);
        mSnapeImage.setImageResource(mSelectedSnape.getImageSrc());
    }
}
