package com.chiragawale.folinsight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiragawale.folinsight.R;
import com.chiragawale.folinsight.entity.Users;

import java.util.List;



public class UserAdapter extends ArrayAdapter<Users> {
    /*
    Constructor
     */
    public UserAdapter(Context context, List<Users> usersList) {
        super(context, 0, usersList);
    }

    @Override
    public View getView(int position,  View view, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = view;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Localizing the text views that display data to set their text
        TextView follower_name = (TextView) listItemView.findViewById(R.id.follower_name);
        TextView follower_userName = (TextView) listItemView.findViewById(R.id.follower_user_name);
        TextView follower_track_date = (TextView) listItemView.findViewById(R.id.follower_tracked_date);
        TextView follower_likes = (TextView) listItemView.findViewById(R.id.likes);
        TextView follower_comments = (TextView) listItemView.findViewById(R.id.comments);
        ImageView follower_profile_picture = (ImageView) listItemView.findViewById(R.id.profile_picture);

        //Get the current follower object
        Users currentUser = getItem(position);

        //Setting the texts of their respective textViews according to the data received from the List
        follower_name.setText(currentUser.getFullName());
        follower_userName.setText(currentUser.getUserName());
       // follower_track_date.setText(currentUser.getDate_trackedFrom());
        follower_comments.setText("Comments : "+ String.valueOf(currentUser.getCommentsPosted()));
        follower_likes.setText("Likes : " + String.valueOf(currentUser.getLikesPosted()));
        //Loads images from instagram site
        Glide.with(getContext()).load(currentUser.getProfilePictureLink()).skipMemoryCache(true).override(144,144).into(follower_profile_picture);

        return listItemView;
    }
}
