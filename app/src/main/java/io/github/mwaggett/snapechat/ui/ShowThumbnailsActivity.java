package io.github.mwaggett.snapechat.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.ShowPhotosActivity;
import io.github.mwaggett.snapechat.adapters.ImageAdapter;
import io.github.mwaggett.snapechat.models.Snape;

public class ShowThumbnailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//      To populate database with images:
//        Snape newSnape2 = new Snape(R.drawable.snape2);
//        newSnape2.save();
//        Snape newSnape3 = new Snape(R.drawable.snape3);
//        newSnape3.save();
//        Snape newSnape4 = new Snape(R.drawable.snape4);
//        newSnape4.save();
//        Snape newSnape5 = new Snape(R.drawable.snape5);
//        newSnape5.save();
//        Snape newSnape6 = new Snape(R.drawable.snape6);
//        newSnape6.save();
//        Snape newSnape7 = new Snape(R.drawable.snape7);
//        newSnape7.save();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_thumbnails);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(ShowThumbnailsActivity.this, ShowPhotosActivity.class);
                intent.putExtra("selected_snape", position);
                startActivity(intent);
            }
        });
    }
}
