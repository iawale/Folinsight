package com.chiragawale.folinsight;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chiragawale.folinsight.entity.Details_ig;

import java.util.List;

/**
 * Created by chira on 7/17/2017.
 */

public class DetailsAdapter extends ArrayAdapter<Details_ig> {
    public DetailsAdapter(Context context, List<Details_ig> details_list) {
        super(context, 0,details_list);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_overview_detail,parent,false);
        }
        //Finding  the textviews to change values
        TextView heading_textView = (TextView) listItemView.findViewById(R.id.heading);
        TextView average_likes_text_view = (TextView) listItemView.findViewById(R.id.average_likes_value);
        TextView average_comments_text_view = (TextView) listItemView.findViewById(R.id.average_comments_value);

        //Getting the current item
        Details_ig currentItem = getItem(position);
        //Setting up the data for views
        heading_textView.setText("For "+currentItem.getDataFor()+":");
        average_likes_text_view.setText(currentItem.getaLikesPer()+"");
        average_comments_text_view.setText(currentItem.getaCommentsPer()+"");

        return listItemView;
    }
}
