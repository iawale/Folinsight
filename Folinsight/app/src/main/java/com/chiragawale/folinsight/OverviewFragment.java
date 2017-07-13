package com.chiragawale.folinsight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.dao.impl.UserDaoImpl;
import com.chiragawale.folinsight.entity.Users;
import com.chiragawale.folinsight.loader.UserLoader;

import java.util.ArrayList;
import java.util.List;


public class OverviewFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Users>>{

    //Adapter for providing data to the list view
    private UserAdapter mUserAdapter;

    //Loader Id for follower loader
    private final static int FOLLOWER_LOADER_ID = 1;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_list,container,false);

        //Progress bar
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        //Initializing the adapter with null list
        mUserAdapter = new UserAdapter(getActivity(), new ArrayList<Users>());

        //Linking the adapter to the list view
        ListView listView = (ListView) rootView.findViewById(R.id.follower_list);
        listView.setAdapter(mUserAdapter);


        //Setting up intent to open profile of selected user from list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users selectedUsers = mUserAdapter.getItem(position);
                //Converting the profile link String to URI and setting the intent up to open the URI accordingly
                Uri uri = Uri.parse(selectedUsers.getProfileLink());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });


        Log.w("Followed By Activity","Logged in" + Keys_Access.getAccessToken());
        getLoaderManager().initLoader(FOLLOWER_LOADER_ID,null,this);


        return rootView;
    }

    //When the loader is created for first time
    @Override
    public Loader<List<Users>> onCreateLoader(int id, Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new UserLoader(getActivity());
    }

    //When loader finishes loading
    @Override
    public void onLoadFinished(Loader<List<Users>> loader, List<Users> data) {
        mUserAdapter.clear();
        mUserAdapter.addAll(data);
        progressBar.setVisibility(View.INVISIBLE);
    }
    //When loader is reset
    @Override
    public void onLoaderReset(Loader<List<Users>> loader) {
        mUserAdapter.clear();
    }
}
