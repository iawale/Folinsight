package com.chiragawale.folinsight;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.chiragawale.folinsight.adapter.DetailsAdapter;
import com.chiragawale.folinsight.keys.GlobalVar;

public class OverviewDetailActivity extends AppCompatActivity {
    TextView heading_textView,aLikesPer_text_view,aCommetsPer_text_view;
    private DetailsAdapter mDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_detail);

        mDetailsAdapter = new DetailsAdapter(this, GlobalVar.userDao.getDataList());

        ListView listView = (ListView) findViewById(R.id.list_detail_overview);
        listView.setAdapter(mDetailsAdapter);
        View empty_vieiw = findViewById(R.id.empty_view);
        listView.setEmptyView(empty_vieiw);

    }


}
