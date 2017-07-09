package com.chiragawale.folinsight;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chiragawale.folinsight.entity.Follower;
import com.chiragawale.folinsight.loader.FollowerLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Follower>> {

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


        //Setting up intent to open profile of selected user from list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Follower selectedFollower = mFollowerAdapter.getItem(position);
                //Converting the profile link String to URI and setting the intent up to open the URI accordingly
                Uri uri = Uri.parse(selectedFollower.getProfileLink());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });


        //Kicks off the loader
        getLoaderManager().initLoader(FOLLOWER_LOADER_ID, null, this);
    }

    //When the loader is created
    @Override
    public Loader<List<Follower>> onCreateLoader(int id, Bundle args) {
        return new FollowerLoader(this);
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
