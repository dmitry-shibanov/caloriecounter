package com.example.caloriecounter.controllers.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.fragments.dialogs.DialogError;
import com.example.caloriecounter.controllers.fragments.dialogs.DialogResult;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Calculate extends Fragment {
    private TextView result;
    private static final String CHANNEL_ID = "dasjdlasl";

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_calorie,container,false);
        setHasOptionsMenu(true);

        final TextInputLayout weightEdit = (TextInputLayout)view.findViewById(R.id.edit_weight);
        weightEdit.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final TextInputLayout heightEdit = (TextInputLayout)view.findViewById(R.id.edit_height);


        Button countCalorie = (Button)view.findViewById(R.id.count);
        countCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BigDecimal weight = new BigDecimal(weightEdit.getEditText().getText().toString());
                    BigDecimal height = new BigDecimal(heightEdit.getEditText().getText().toString());
                    BigDecimal result = new BigDecimal(weight.doubleValue()/Math.pow(height.doubleValue()/100,2));
                    if(result.doubleValue()<=0){
                        throw new ArithmeticException();
                    }
                    DialogResult dialogResult = new DialogResult();
                    Bundle bundle = new Bundle();
                    bundle.putDouble("message",result.doubleValue());
                    dialogResult.setArguments(bundle);
                    dialogResult.show(getFragmentManager(),"DialogResult");
                }catch(ClassCastException exp){
                    DialogError di = DialogError.newInstance("Проверьте правильность введенных данных, везде ли введены числа","Проверьте данные");
                    di.show(getFragmentManager(),"DialogError");
                }catch (ArithmeticException exp){
                    DialogError di = DialogError.newInstance("Проверьте правильность введенных данных, значения меньше или равна 0 недопустимы","Проверьте данные");
                    di.show(getFragmentManager(),"DialogError");
                }
            }
        });


        return view;
    }
}
