package com.example.caloriecounter.controllers.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.caloriecounter.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * DateFragment это класс для определения логики DialogFragment, вызывая его мы получаем окно с
 * диалогом для выбора даты.
 */
public class DateFragment extends DialogFragment {

    /**
     * Переменная для хранения даты в bundle
     */
    private static final String ARG_DATE = "date";


    /**
     * переменная через которую в activityResult получем наше выбранное значение.
     */
    public static final String EXTRA_DATE = "extra";


    /**
     * DatePicker переменная DatePicker во view.
     */
    private DatePicker mDatePicker;
    private int resultCode;
    private Date date;


    /**
     * @param resultCode - результат операции.
     * @param date - Дата, выбранная пользователем
     */
    private void sendResult(int resultCode, Date date){
        this.resultCode = resultCode;
        this.date = date;
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

    /**
     * @param date - дата, выбранная пользователем.
     * @return Возвращает view с выбором даты @see DateFragment
     */
    public static DateFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);

        DateFragment fragment = new DateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Date date = (Date)getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_date, null);

        mDatePicker = (DatePicker)v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("Выберите дату").setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
            int year1 = mDatePicker.getYear();
            int month1 = mDatePicker.getMonth();
            int day1 = mDatePicker.getDayOfMonth();

            Date date1 = new GregorianCalendar(year1, month1, day1).getTime();

            sendResult(Activity.RESULT_OK, date1);
        }).create();
    }
}
