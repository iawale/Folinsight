package com.chiragawale.folinsight;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chiragawale.folinsight.entity.Follower;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Follower>> {
    private final String FOLLOWER_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=3883966149.9708451.f09f4c2b9a014205a5d4eb91a4841226";

    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.test);

        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<List<Follower>> onCreateLoader(int id, Bundle args) {
        return new FollowerLoader(this,FOLLOWER_DATA_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Follower>> loader, List<Follower> data) {
    test.setText(data.get(0).getFullName());
    }

    @Override
    public void onLoaderReset(Loader<List<Follower>> loader) {
        test.setText("Reset");
    }
}
