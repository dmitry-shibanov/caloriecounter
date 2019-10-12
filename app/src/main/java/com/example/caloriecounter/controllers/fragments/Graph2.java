package com.example.caloriecounter.controllers.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.caloriecounter.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Graph2 extends Fragment {


    public Graph2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph2, container, false);

        ListView listView = (ListView) view.findViewById(R.id.graphs_list);

        ArrayList<BarData> list = new ArrayList<>();

        for(int i = 0;i<2;i++){
            list.add(generateData(i+1));
        }

        ChartDataAdapter adapter = new ChartDataAdapter(getContext(),list);
        listView.setAdapter(adapter);

        return view;
    }

    private class ChartDataAdapter extends ArrayAdapter<BarData>{
        public ChartDataAdapter(Context context, List<BarData> objects){
            super(context,0,objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BarData data = getItem(position);

            ViewHolder holder = null;

            if(convertView == null){
                holder = new ViewHolder();

                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.item_barchart,null);
                holder.chart = (BarChart)convertView.findViewById(R.id.chart_to_time);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            data.setValueTextColor(Color.BLACK);
            holder.chart.getDescription().setEnabled(false);
            holder.chart.setDrawGridBackground(false);

            XAxis xAxis = holder.chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);

            YAxis leftAxis = holder.chart.getAxisLeft();
            leftAxis.setLabelCount(5,false);
            leftAxis.setSpaceTop(15f);

            YAxis rightAxis = holder.chart.getAxisRight();
            rightAxis.setLabelCount(5,false);
            rightAxis.setSpaceTop(15f);

            holder.chart.setData(data);
            holder.chart.setFitBars(true);

            holder.chart.animateY(500);

            return convertView;

        }
    }

    private class ViewHolder{
        BarChart chart;
    }

    private BarData generateData(int count){
        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0;i<12;i++){
            entries.add(new BarEntry(i, (float)(Math.random()*70)+30));
        }

        BarDataSet dataSet = new BarDataSet(entries,"New Data set "+count);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setBarBorderColor(Color.rgb(203,203,203));

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);
        return data;
    }

}
