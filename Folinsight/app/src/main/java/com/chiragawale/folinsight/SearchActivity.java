package com.chiragawale.folinsight;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chiragawale.folinsight.adapter.UserAdapter;
import com.chiragawale.folinsight.keys.GlobalVar;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Getting data from the Intent for searching
        Intent intent = getIntent();
        Uri query = intent.getData();
        setTitle("Results: ");
        //Getting refrence of the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        //Setting up the adapter
        UserAdapter userAdapter = new UserAdapter(this, GlobalVar.userDao.getSearchResultList(query.toString()));

        //Getting reference for listview and setting it up
        ListView listView = (ListView) findViewById(R.id.follower_list);
        listView.setAdapter(userAdapter);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
        //Changing the text to suit the class
        TextView subheadingEmptyView = (TextView)  findViewById(R.id.empty_subtitle_text);
        subheadingEmptyView.setText("No results found");
        progressBar.setVisibility(View.INVISIBLE);
    }
}
