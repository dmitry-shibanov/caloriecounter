package com.example.caloriecounter.controllers.fragments.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.caloriecounter.R;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.GregorianCalendar;

public class DialogResult extends DialogFragment {
    private TextView mResult;
    private TextView mDescription;
    private ImageView mMood;
    private double index;

    public DialogResult() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Drawable loadDrawable(String mood) {
        try {
            InputStream ims = getActivity().getAssets().open("mood/" + mood);
            Drawable d = Drawable.createFromStream(ims, null);
            return d;
        } catch (IOException ex) {
            return null;
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_index_result, null);


        BigDecimal bd = new BigDecimal(getArguments().getDouble("message")).setScale(2, RoundingMode.HALF_UP);
        index = bd.doubleValue();
        StringBuilder mood = new StringBuilder();
        mResult = (TextView) view.findViewById(R.id.index_result);
        mResult.setText(String.valueOf(index));

        mDescription = (TextView) view.findViewById(R.id.description_result);

        if (index < 18.5) {
            mDescription.setText("У вас пониженный вес");
            mood.append("angry.png");
        } else if (index > 30) {
            mDescription.setText("У вас повышенный вес");
            mood.append("angry.png");
        } else {
            if (index < 19.5) {
                mood.append("meh.png");
            }
            if (index >= 28.5) {
                mood.append("meh.png");
            }
            if (mood.toString().length() == 0) {
                mood.append("smile.png");
            }
            mDescription.setText("У вас нормальный вес");
        }

        final ImageView mMood = (ImageView) view.findViewById(R.id.mood);
        mMood.setImageDrawable(loadDrawable(mood.toString()));

        return new AlertDialog.Builder(getActivity()).setView(view).setTitle("Ваш индекс").setPositiveButton(android.R.string.ok, null).create();
    }

}
