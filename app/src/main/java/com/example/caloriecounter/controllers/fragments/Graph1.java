package com.example.caloriecounter.controllers.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.fragments.dialogs.DialogError;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Graph1 extends Fragment {

    private PieChart pieChart;
    private TextView mBeginDate;
    private TextView mEndDate;
    private Date dateFirst;
    private Date dateSecond;

    public Graph1() {

    }

    private void checkCondionDates(){
        long result = dateSecond.getTime() - dateFirst.getTime();
        if(result<=0){
            DialogError di = DialogError.newInstance("Проверьте даты", "Дата начала периода больше даты конца");
            di.show(getFragmentManager(),"DialogError");
        }else{
            checkDb();
        }
    }

    private void checkDb(){

    }

    private String localDate(Date date) {

        int year = date.getYear() + 1900;
        int month = date.getMonth();
        int day = date.getDate();

        return day + "." + month + "." + year;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 0) {
            dateFirst = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            mBeginDate.setText(localDate(dateFirst));
        }
        if (requestCode == 1) {
            dateSecond = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            mBeginDate.setText(localDate(dateSecond));
        }

        if(dateFirst!=null && dateSecond!=null){
            checkCondionDates();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_graph1, container, false);

        mBeginDate = (TextView) view.findViewById(R.id.begin_date);
        mBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DateFragment fragment = DateFragment.newInstance(new Date());
                fragment.setTargetFragment(Graph1.this, 0);
                fragment.show(manager, "DialogDate");
            }
        });

        mEndDate = (TextView) view.findViewById(R.id.end_date);
        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DateFragment fragment = DateFragment.newInstance(new Date());
                fragment.setTargetFragment(Graph1.this, 1);
                fragment.show(manager, "DialogDate");
            }
        });

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
