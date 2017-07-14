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


public class OverviewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview,container,false);

        return rootView;
    }


}
