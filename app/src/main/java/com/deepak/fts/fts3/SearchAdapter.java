package com.deepak.fts.fts3;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.deepak.fts.R;
import com.deepak.fts.database.ConnectDB;
import com.deepak.fts.model.User;

import java.util.ArrayList;

/**
 * Created by Deepak Goyal on 24/1/17.
 * Author: Deepak Goyal
 */
public class SearchAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<User> mList;

    public SearchAdapter(Context context) {
        this.context = context;
        this.mList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (mList != null && mList.size() > 0)
            return mList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_user, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.email = (TextView) convertView.findViewById(R.id.email);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User user = mList.get(position);

        viewHolder.name.setText(user.getName());
        viewHolder.email.setText(user.getEmail());
        viewHolder.desc.setText(user.getDesc());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (!TextUtils.isEmpty(constraint)) {
                    ArrayList<User> orders1 = searchUsers(context, constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = orders1;
                    filterResults.count = orders1.size();
                }
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    mList = (ArrayList<User>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    private ArrayList<User> searchUsers(Context context, String s) {
        return ConnectDB.getInstance(context).searchFTS3(s);
    }

    private class ViewHolder {
        private TextView name;
        private TextView email;
        private TextView desc;
    }
}
