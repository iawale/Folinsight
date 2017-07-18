package com.chiragawale.folinsight;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.chiragawale.folinsight.entity.Users;
import com.chiragawale.folinsight.fragment.OverviewFragment;
import com.chiragawale.folinsight.loader.UserLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Users>> {
    ProgressBar progressBar;
    FloatingActionButton fab;
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

        // Setup FAB to open EditorActivity
         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OverviewDetailActivity.class);
                startActivity(intent);
            }
        });


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
                getLoaderManager().destroyLoader(0);
                GlobalVar.userDao.clearUserList();
                GlobalVar.mediaDao.resetValues();
                GlobalVar.webView.loadUrl("https://www.instagram.com/accounts/logout");
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.refresh:
                //Resets all values
                GlobalVar.userDao.clearUserList();
                GlobalVar.mediaDao.resetValues();
                //Kicks Off the Loader
                getLoaderManager().restartLoader(0,null,this);
                Log.e("Refresh", "====================================================");
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
        //Check for error
        if(GlobalVar.error429){
            Toast.makeText(this, "Too many requests to Api in an hour, please try again in the next hour", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Load complete", Toast.LENGTH_SHORT).show();
        }
        //Set up lists with fresh data
        GlobalVar.userDao.setUpUserLists(data);
        // Set total follower mutual and follows values
        GlobalVar.mediaDao.setFansCount(GlobalVar.userDao.getFollowedByList().size());
        GlobalVar.mediaDao.setMutualCount(GlobalVar.userDao.getMutualList().size());
        GlobalVar.mediaDao.setFollowsCount(GlobalVar.userDao.getFollowsList().size());


        OverviewFragment.setValues();
        progressBar.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.VISIBLE);
    }
    //Onloader Reset
    @Override
    public void onLoaderReset(android.content.Loader<List<Users>> loader) {
        Log.e("Loader r main","==========================================================");
        GlobalVar.userDao.clearUserList();
    }

}
