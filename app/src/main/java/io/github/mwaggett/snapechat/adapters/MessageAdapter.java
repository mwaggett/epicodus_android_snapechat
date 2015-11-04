package io.github.mwaggett.snapechat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.mwaggett.snapechat.R;
import io.github.mwaggett.snapechat.models.Message;

public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Message> mMessages;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        mContext = context;
        mMessages = messages;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.message_view, null);
            holder = new ViewHolder();
            holder.mReceiverLabel = (TextView) convertView.findViewById(R.id.receiverLabel);
            holder.mSenderLabel = (TextView) convertView.findViewById(R.id.senderLabel);
            holder.mSentAtLabel = (TextView) convertView.findViewById(R.id.sentAtLabel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Message message = mMessages.get(position);
        holder.mReceiverLabel.setText("To: " + message.getReceiver().getName());
        holder.mSenderLabel.setText("From: " + message.getSender().getName());
        holder.mSentAtLabel.setText(message.getFormattedCreatedAt());

        return convertView;
    }

    public static class ViewHolder {
        TextView mReceiverLabel;
        TextView mSenderLabel;
        TextView mSentAtLabel;
    }
}
