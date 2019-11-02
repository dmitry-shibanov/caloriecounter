package com.example.caloriecounter.controllers.fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Graph1 extends Fragment {

    private PieChart pieChart;
    private TextView mBeginDate;
    private TextView mEndDate;

    public Graph1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph1, container, false);

        mBeginDate = (TextView) view.findViewById(R.id.begin_date);

        mEndDate = (TextView)view.findViewById(R.id.end_date);

        pieChart = (PieChart) view.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.15f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);


        List<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(34f, "Пицца"));
        yValues.add(new PieEntry(21f, "Сок"));
        yValues.add(new PieEntry(54f, "Лемонад"));
        yValues.add(new PieEntry(14f, "Мясо"));
        yValues.add(new PieEntry(12f, "KFC"));

        PieDataSet dataSet = new PieDataSet(yValues, "Food");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

        return view;
    }

}
