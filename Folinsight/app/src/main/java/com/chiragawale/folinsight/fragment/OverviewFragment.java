package com.chiragawale.folinsight.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiragawale.folinsight.keys.GlobalVar;
import com.chiragawale.folinsight.R;


public class OverviewFragment extends Fragment {
    public static TextView totalPosts,totalLikes,totalComments,
    fanLikes, fanComments, followsLikes, followsComments,
    strangerLikes,strangerComments,fansCount,followsCount,mutualCount,mutualLikes,mutualComments;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview,container,false);
        //For values for total:
        totalPosts = (TextView) rootView.findViewById(R.id.total_posts);
        totalLikes = (TextView) rootView.findViewById(R.id.total_likes_value);
        totalComments = (TextView) rootView.findViewById(R.id.total_comments_value);
        //For Mutual
        mutualLikes = (TextView) rootView.findViewById(R.id.mutual_likes_value);
        mutualComments = (TextView) rootView.findViewById(R.id.mutual_comments_value);
        /**
         * Localizing
         */
        //For  likes
        fanLikes = (TextView) rootView.findViewById(R.id.fans_likes_value);
        fanComments = (TextView) rootView.findViewById(R.id.fans_comments_value);
        //For Follows:
        followsLikes = (TextView) rootView.findViewById(R.id.follows_likes_value);
        followsComments = (TextView) rootView.findViewById(R.id.follows_comments_value);
        //For Strangers:
        strangerLikes = (TextView) rootView.findViewById(R.id.strangers_likes_value);
        strangerComments = (TextView) rootView.findViewById(R.id.strangers_comments_value);

        fansCount = (TextView) rootView.findViewById(R.id.fans_value);
        followsCount = (TextView) rootView.findViewById(R.id.follows_value);
        mutualCount = (TextView) rootView.findViewById(R.id.mutual_value);
        setValues();
        return rootView;
    }

    //Set the values of text views of the fragment
    public static void setValues(){

        totalPosts.setText("("+GlobalVar.mediaDao.getTotalPosts()+")");
        totalLikes.setText(GlobalVar.mediaDao.getTotalLikes()+"");
        totalComments.setText(GlobalVar.mediaDao.getTotalComments()+"");

        mutualLikes.setText(GlobalVar.mediaDao.getMutualLikes()+"");
        mutualComments.setText(GlobalVar.mediaDao.getMutualComments()+"");

        fanLikes.setText(GlobalVar.mediaDao.getFanLikes()+"");
        fanComments.setText(GlobalVar.mediaDao.getFanComments()+"");

        followsLikes.setText(GlobalVar.mediaDao.getFollowsLikes()+"");
        followsComments.setText(GlobalVar.mediaDao.getFollowsComments()+"");

        strangerLikes.setText(GlobalVar.mediaDao.getStrangerLikes()+"");
        strangerComments.setText(GlobalVar.mediaDao.getStrangerComments()+"");

        fansCount.setText(GlobalVar.mediaDao.getFansCount()+"");
        followsCount.setText(GlobalVar.mediaDao.getFollowsCount()+"");
        mutualCount.setText(GlobalVar.mediaDao.getMutualCount()+"");
    }


}
