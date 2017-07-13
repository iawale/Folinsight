package com.chiragawale.folinsight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.dao.impl.UserDaoImpl;
import com.chiragawale.folinsight.entity.Users;
import com.chiragawale.folinsight.loader.UserLoader;

import java.util.List;


public class OverviewFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Users>>{

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview,container,false);
        textView = (TextView) rootView.findViewById(R.id.over_view);
        getLoaderManager().initLoader(0,null,this);
        return rootView;
    }


    @Override
    public Loader<List<Users>> onCreateLoader(int id, Bundle args) {
        return new UserLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Users>> loader, List<Users> data) {
        GlobalVar.userDao.setUserList(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Users>> loader) {
        GlobalVar.userDao.clearUserList();
    }
}
