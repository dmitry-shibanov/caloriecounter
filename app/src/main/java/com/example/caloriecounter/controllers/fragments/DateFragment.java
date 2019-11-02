package com.example.caloriecounter.controllers.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.caloriecounter.R;

public class DateFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_date, null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("Выберите дату").setPositiveButton(android.R.string.ok, null).create();
    }
}
