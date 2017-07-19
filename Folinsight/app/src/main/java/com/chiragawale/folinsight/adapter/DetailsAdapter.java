package com.chiragawale.folinsight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chiragawale.folinsight.R;
import com.chiragawale.folinsight.entity.Details_ig;
import com.chiragawale.folinsight.keys.GlobalVar;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chira on 7/17/2017.
 */

public class DetailsAdapter extends ArrayAdapter<Details_ig> {


    public DetailsAdapter(Context context, List<Details_ig> details_list) {
        super(context, 0, details_list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_overview_detail, parent, false);
        }
        //Finding  the textviews to change values
        TextView heading_textView = (TextView) listItemView.findViewById(R.id.heading);
        TextView average_likes_text_view = (TextView) listItemView.findViewById(R.id.average_likes_value);
        TextView average_comments_text_view = (TextView) listItemView.findViewById(R.id.average_comments_value);


        //Getting the current item
        Details_ig currentItem = getItem(position);
        //Setting up the data for views
        heading_textView.setText("Per " + currentItem.getDataFor_code() + ":");
        average_likes_text_view.setText(currentItem.getaLikesPer() + "");
        average_comments_text_view.setText(currentItem.getaCommentsPer() + "");

        /**
         * Setting up the graphs
         */
        GraphView graphView = (GraphView) listItemView.findViewById(R.id.graphView);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);

        List<DataPoint> likesDataPointList = getDataPointList(currentItem);

        DataPoint dataPoints[] = new DataPoint[likesDataPointList.size()];
        dataPoints = likesDataPointList.toArray(dataPoints);

        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(dataPoints);
        graphView.addSeries(lineGraphSeries);

        return listItemView;
    }


    List<DataPoint> getDataPointList(Details_ig currentItem) {
        List<DataPoint> likesDataPointsList = new ArrayList<>();
        int i = 0;
        switch (currentItem.getDataFor_code()) {
            case 100:
                for (Details_ig current : GlobalVar.dataDao.getPostDataList()) {
                    likesDataPointsList.add(new DataPoint(i, current.getaLikesPer()));
                    i++;
                }
                break;
            case 101:
                for (Details_ig current : GlobalVar.dataDao.getFollowerDataList()) {
                    likesDataPointsList.add(new DataPoint(i, current.getaLikesPer()));
                    i++;
                }
                break;
            case 102:
                for (Details_ig current : GlobalVar.dataDao.getMutualDataList()) {
                    likesDataPointsList.add(new DataPoint(i, current.getaLikesPer()));
                    i++;
                }
                break;
            case 103:
                for (Details_ig current : GlobalVar.dataDao.getFanDataList()) {
                    likesDataPointsList.add(new DataPoint(i, current.getaLikesPer()));
                    i++;
                }
                break;
            case 104:
                for (Details_ig current : GlobalVar.dataDao.getFollowsDataList()) {
                    likesDataPointsList.add(new DataPoint(i, current.getaLikesPer()));
                    i++;
                }
                break;
            default:
                for (Details_ig current : GlobalVar.dataDao.getStrangerDataList()) {
                    likesDataPointsList.add(new DataPoint(i, current.getaLikesPer()));
                    i++;
                }
                break;

        }
        return likesDataPointsList;
    }
}
