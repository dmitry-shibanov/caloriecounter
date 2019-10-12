package com.example.caloriecounter.controllers.fragments.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.caloriecounter.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DialogResult extends DialogFragment {
    private TextView mResult;
    private TextView mDescription;
    private double index;

    public DialogResult() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_index_result, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        BigDecimal bd = new BigDecimal(getArguments().getDouble("message")).setScale(2, RoundingMode.HALF_UP);
        index = bd.doubleValue();

        mResult = (TextView)view.findViewById(R.id.index_result);
        mResult.setText(String.valueOf(index));

        mDescription = (TextView)view.findViewById(R.id.description_result);

        if(index<18.5){
            mDescription.setText("У вас пониженный вес");
        }else if(index>30){
            mDescription.setText("У вас повышенный вес");
        }else {
            mDescription.setText("У вас нормальный вес");
        }

        return view;
    }
}
