package io.github.mwaggett.snapechat.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Random;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.models.Snape;

public class ShowPhotosActivity extends AppCompatActivity {

    @Bind(R.id.currentSnape) ImageView mImage;
    @Bind(R.id.nextButton) Button mNextButton;
    @Bind(R.id.previousButton) Button mPreviousButton;
    @Bind(R.id.randomButton) Button mRandomButton;

    private ArrayList<Snape> mSnapeLib;
    private int mClickedSnapePosition;
    private Snape mCurrentSnape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);
        ButterKnife.bind(this);

        Snape.all(new Runnable() {
            @Override
            public void run() {
                mSnapeLib = (ArrayList<Snape>) Snape.getAllSnapes();
                mClickedSnapePosition = getIntent().getExtras().getInt("selected_snape");
                mCurrentSnape = mSnapeLib.get(mClickedSnapePosition);

                setLayoutContent();
            }
        });

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
                intent.putExtra("snapeId", mCurrentSnape.getId());
                startActivity(intent);
            }
        });
    }

    private void setLayoutContent() {
        mImage.setImageResource(mCurrentSnape.getImageSrc());
    }

}
