package io.github.mwaggett.snapechat;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import io.github.mwaggett.snapechat.SnapeLib;

public class ShowPhotosActivity extends AppCompatActivity {

    private ImageView mImage;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mRandomButton;
    private SnapeLib mSnapeLib;
    private int mCurrentSnape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        mImage = (ImageView) findViewById(R.id.currentSnape);
        mNextButton = (Button) findViewById(R.id.nextButton);
        mPreviousButton = (Button) findViewById(R.id.previousButton);
        mRandomButton = (Button) findViewById(R.id.randomButton);
        mSnapeLib = new SnapeLib();
        mCurrentSnape = mSnapeLib.getSnapes().get(getIntent().getExtras().getInt("selected_snape"));

        setLayoutContent();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSnape = mSnapeLib.nextSnape(mCurrentSnape);
                setLayoutContent();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSnape = mSnapeLib.previousSnape(mCurrentSnape);
                setLayoutContent();
            }
        });

        mRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSnape = mSnapeLib.randomSnape();
                setLayoutContent();
            }
        });
    }

    private void setLayoutContent() {
        mImage.setImageResource(mCurrentSnape);
    }

}
