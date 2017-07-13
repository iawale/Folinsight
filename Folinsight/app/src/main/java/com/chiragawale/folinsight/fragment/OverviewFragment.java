package com.chiragawale.folinsight.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chiragawale.folinsight.GlobalVar;
import com.chiragawale.folinsight.R;
import com.chiragawale.folinsight.entity.Users;
import com.chiragawale.folinsight.loader.UserLoader;

import java.util.List;


public class OverviewFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Users>>{
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview,container,false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        //Kicks Off the Loader
        getLoaderManager().initLoader(0,null,this);
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
        GlobalVar.userDao.setUpUserLists(data);
        progressBar.setVisibility(View.INVISIBLE);
    }
    //When loader is reset
    @Override
    public void onLoaderReset(Loader<List<Users>> loader) {
        GlobalVar.userDao.clearUserList();
    }
}
