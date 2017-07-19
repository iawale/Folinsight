package com.chiragawale.folinsight.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chiragawale.folinsight.fragment.FollowedByFragment;
import com.chiragawale.folinsight.fragment.FollowsFragment;
import com.chiragawale.folinsight.fragment.MutualFragment;
import com.chiragawale.folinsight.fragment.OverviewFragment;

/**
 * Created by chira on 7/12/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
    /**

 * For the names of the tabs
 */
private String tabTitles[] = new String[] { "Overview","Mutual","Fans", "Follows"};
    private Context mcontext;
    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mcontext = context;
    }

    /**
     *
     * @param position denotes the activity the user is in
     * @return returns the fragment according to the position requested by the program
     */

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new OverviewFragment();
        }else if (position == 1){
            return new MutualFragment();
        }else if (position == 2){
            return new FollowedByFragment();
        }else{
            return new FollowsFragment();
        }

    }

    /**
     *
     * @return Number of pages
     */
    @Override
    public int getCount() {
        return 4;
    }

    /**
     *
     * @param position Reference to the page the user is in
     * @return         Title of the page the user is in
     */
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


}

