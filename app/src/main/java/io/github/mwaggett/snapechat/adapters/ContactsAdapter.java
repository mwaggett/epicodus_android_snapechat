package io.github.mwaggett.snapechat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.models.User;

public class ContactsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<User> mContacts;

    public ContactsAdapter(Context context, ArrayList<User> contacts) {
        mContext = context;
        mContacts = contacts;
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    //not needed rn
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_view, null);
            holder = new ViewHolder();
            holder.mContactNameLabel = (TextView) convertView.findViewById(R.id.contactNameLabel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final User user = mContacts.get(position);
        holder.mContactNameLabel.setText(user.getName());

        return convertView;
    }

    public static class ViewHolder {
        TextView mContactNameLabel;
    }
}
