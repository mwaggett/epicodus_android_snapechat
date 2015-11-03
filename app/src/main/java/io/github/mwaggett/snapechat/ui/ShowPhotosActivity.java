package io.github.mwaggett.snapechat;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Random;

import io.github.mwaggett.snapechat.models.Snape;
import io.github.mwaggett.snapechat.ui.NewMessageActivity;

public class ShowPhotosActivity extends AppCompatActivity {

    private ImageView mImage;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mRandomButton;
    private ArrayList<Snape> mSnapeLib;
    private int mClickedSnapePosition;
    private Snape mCurrentSnape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        mImage = (ImageView) findViewById(R.id.currentSnape);
        mNextButton = (Button) findViewById(R.id.nextButton);
        mPreviousButton = (Button) findViewById(R.id.previousButton);
        mRandomButton = (Button) findViewById(R.id.randomButton);
        mSnapeLib = (ArrayList) Snape.all();
        mClickedSnapePosition = getIntent().getExtras().getInt("selected_snape");
        mCurrentSnape = mSnapeLib.get(mClickedSnapePosition);

        setLayoutContent();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickedSnapePosition == mSnapeLib.size()-1) {
                    mClickedSnapePosition = 0;
                } else {
                    mClickedSnapePosition++;
                }
                mCurrentSnape = mSnapeLib.get(mClickedSnapePosition);
                setLayoutContent();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickedSnapePosition == 0) {
                    mClickedSnapePosition = mSnapeLib.size()-1;
                } else {
                    mClickedSnapePosition--;
                }
                mCurrentSnape = mSnapeLib.get(mClickedSnapePosition);
                setLayoutContent();
            }
        });

        mRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomGenerator = new Random();
                mClickedSnapePosition = randomGenerator.nextInt(mSnapeLib.size());
                mCurrentSnape = mSnapeLib.get(mClickedSnapePosition);
                setLayoutContent();
            }
        });

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowPhotosActivity.this, NewMessageActivity.class);
                intent.putExtra("snape", mCurrentSnape);
                startActivity(intent);
            }
        });
    }

    private void setLayoutContent() {
        mImage.setImageResource(mCurrentSnape.getImageSrc());
    }

}
