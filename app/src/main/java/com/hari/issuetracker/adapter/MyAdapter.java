package com.hari.issuetracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.hari.issuetracker.R;
import com.hari.issuetracker.model.IssueListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<IssueListItem> values;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtIssueTitle;
        public TextView txtIssueDesc;
        public TextView txtIssueAuthorName;
        public TextView txtIssueLastUpdate;
        public ImageView imgAvatar;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtIssueTitle = (TextView) v.findViewById(R.id.issue_title);
            txtIssueDesc = (TextView) v.findViewById(R.id.issue_desc);
            txtIssueAuthorName = (TextView) v.findViewById(R.id.issue_author_name);
            txtIssueLastUpdate = (TextView) v.findViewById(R.id.issue_last_update);
            imgAvatar = (ImageView) v.findViewById(R.id.issue_avatar);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<IssueListItem> myDataset, Context ctx) {
        values = myDataset;
        mContext = ctx;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.item_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final IssueListItem item = values.get(position);
        holder.txtIssueTitle.setText(item.getTitle());
        holder.txtIssueDesc.setText(item.getBody());
        holder.txtIssueAuthorName.setText(item.getUser().getLogin());
        holder.txtIssueLastUpdate.setText(item.getUpdated_at());
        Picasso.with(mContext).load(item.getUser().getAvatar_url()).into(holder.imgAvatar);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}