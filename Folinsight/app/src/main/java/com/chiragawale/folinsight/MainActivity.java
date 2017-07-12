package com.chiragawale.folinsight;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
    }
}
