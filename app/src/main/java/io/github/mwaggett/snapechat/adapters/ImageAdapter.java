package io.github.mwaggett.snapechat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;
import io.github.mwaggett.snapechat.models.Snape;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Snape> mSnapes;

    public ImageAdapter(Context context, ArrayList<Snape> snapes) {
        mContext = context;
        mSnapes = snapes;
    }

    public int getCount() {
        return mSnapes.size();
    }

    // not needed
    public Object getItem(int position) {
        return null;
    }

    // not needed
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mSnapes.get(position).getImageSrc());
        return imageView;
    }
}
