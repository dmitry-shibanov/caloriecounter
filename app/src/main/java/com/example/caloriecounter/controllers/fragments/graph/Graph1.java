package com.example.caloriecounter.controllers.fragments.graph;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caloriecounter.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;

public class Graph1 extends Fragment {

    public static final String DATE_1 = "DATE_1";
    public static final String DATE_2 = "DATE_2";

    private PieChart pieChart;

    public static Graph1 newInstance(Date date1, Date date2) {

        Bundle args = new Bundle();
        args.putSerializable(DATE_1,date1);
        args.putSerializable(DATE_2,date2);
        Graph1 fragment = new Graph1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_graph1, container, false);

        Date date1 = (Date)getArguments().getSerializable(DATE_1);
        Date date2 = (Date)getArguments().getSerializable(DATE_2);

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
