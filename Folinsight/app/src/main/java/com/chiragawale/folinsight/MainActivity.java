package com.chiragawale.folinsight;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.chiragawale.folinsight.entity.Users;
import com.chiragawale.folinsight.fragment.OverviewFragment;
import com.chiragawale.folinsight.loader.UserLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Users>> {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * find the view pager that will allow the users to swipe between activities
         */
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        /*
        Setting up the adapter that gives the view pager the views as it calls for them
         */
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        /*
        Assigning the adapter to the viewpager
         */
        viewPager.setAdapter(adapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);

        //Setting up progress bar
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


    }
    //Inflates the menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Handles the clicks on menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Log.e("Logout", "=========================================================");
                GlobalVar.webView.loadUrl("https://www.instagram.com/accounts/logout");
                finish();
                return true;
            case R.id.refresh:
                Log.e("Refresh", "====================================================");
                //Kicks Off the Loader
                getLoaderManager().initLoader(0, null, this);
                return true;
        }
        return false;
    }


    //When the loader is created for first time
    @Override
    public Loader<List<Users>> onCreateLoader(int id, Bundle args) {

        progressBar.setVisibility(View.VISIBLE);
        return new UserLoader(MainActivity.this);
    }

    //when loader finishes loading
    @Override
    public void onLoadFinished(android.content.Loader<List<Users>> loader, List<Users> data) {
        GlobalVar.userDao.clearUserList();
        //Set up lists with fresh data
        GlobalVar.userDao.setUpUserLists(data);
        // Set total follower mutual and follows values
        GlobalVar.mediaDao.setFansCount(GlobalVar.userDao.getFollowedByList().size());
        GlobalVar.mediaDao.setMutualCount(GlobalVar.userDao.getMutualList().size());
        GlobalVar.mediaDao.setFollowsCount(GlobalVar.userDao.getFollowsList().size());


        OverviewFragment.setValues();

        progressBar.setVisibility(View.INVISIBLE);
    }
    //Onloader Reset
    @Override
    public void onLoaderReset(android.content.Loader<List<Users>> loader) {

    }

}
