package com.chiragawale.folinsight;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.chiragawale.folinsight.entity.Follower;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Follower>> {
    /*
    URL TO CONNECT TO, TO GET FOLLOWER DATA
     */
    private final String FOLLOWER_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=3883966149.9708451.f09f4c2b9a014205a5d4eb91a4841226";
    //Adapter for providing data to the list view
    private FollowerAdapter mFollowerAdapter;

    //Loader Id for follower loader
    private final static int FOLLOWER_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the adapter with null list
        mFollowerAdapter = new FollowerAdapter(this, new ArrayList<Follower>());

        //Linking the adapter to the list view
        ListView listView = (ListView) findViewById(R.id.follower_list);
        listView.setAdapter(mFollowerAdapter);

        //Kicks off the loader
        getLoaderManager().initLoader(FOLLOWER_LOADER_ID, null, this);
    }

    //When the loader is created
    @Override
    public Loader<List<Follower>> onCreateLoader(int id, Bundle args) {
        return new FollowerLoader(this, FOLLOWER_DATA_URL);
    }

    //When the loader is done loading the data
    @Override
    public void onLoadFinished(Loader<List<Follower>> loader, List<Follower> data) {
        mFollowerAdapter.clear();

        mFollowerAdapter.addAll(data);
    }

    //When the loader is reset
    @Override
    public void onLoaderReset(Loader<List<Follower>> loader) {

        mFollowerAdapter.clear();
    }
}
