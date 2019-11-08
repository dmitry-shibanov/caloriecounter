package com.example.caloriecounter.controllers.fragments.dialogs;

import android.app.Dialog;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.fragments.DateFragment;

import org.w3c.dom.Text;

import java.util.Date;

public class DialogError extends DialogFragment {

    private static final String MESSAGE = "message";
    private static final String ERROR = "message";

    private TextView mErrorContent;
    private TextView mErrorTitle;

    private String content;
    private String title;

    public static DialogError newInstance(String message, String error){
        Bundle args = new Bundle();
        args.putString(MESSAGE, message);
        args.putString(ERROR, error);

        DialogError fragment = new DialogError();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog_exceptions, null, false);

        mErrorContent = (TextView)view.findViewById(R.id.error_description);
        mErrorTitle = (TextView)view.findViewById(R.id.title_error);

        if(savedInstanceState!=null){
            content = savedInstanceState.getString(MESSAGE);
            title = savedInstanceState.getString(ERROR);
        }

        mErrorContent.setText(content);
        mErrorTitle.setText(title);

        return new AlertDialog.Builder(getActivity()).setView(view).setTitle("Ошибка").setPositiveButton(android.R.string.ok, null).create();
    }
}
