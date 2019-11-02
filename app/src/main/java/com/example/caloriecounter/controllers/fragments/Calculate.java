package com.example.caloriecounter.controllers.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.fragments.dialogs.DialogError;
import com.example.caloriecounter.controllers.fragments.dialogs.DialogResult;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;

public class Calculate extends Fragment {
    private TextView result;
    private static final String CHANNEL_ID = "dasjdlasl";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
            CharSequence name = "Any title";

//            String description = getString(R.string.channel_description);
            String description = "Any no long description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    int countCalories(int calorie){
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_calorie,container,false);


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
                createNotificationChannel();

//                Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
//                snoozeIntent.setAction(ACTION_SNOOZE);
//                snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
//                PendingIntent snoozePendingIntent =
//                        PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
//
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                        .setSmallIcon(R.drawable.notification_icon)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!")
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                        .setContentIntent(pendingIntent)
//                        .addAction(R.drawable.ic_snooze, getString(R.string.snooze),
//                                snoozePendingIntent);

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
                    Bundle bundle = new Bundle();
                    bundle.putString("error", "Проверьте данные");
                    bundle.putString("message", "Проверьте правильность введенных данных, везде ли введены числа");
                    DialogError di = new DialogError();
                    di.show(getFragmentManager(),"DialogError");
                    di.setArguments(bundle);

                }catch (ArithmeticException exp){
                    Bundle bundle = new Bundle();
                    bundle.putString("error", "Проверьте данные");
                    bundle.putString("message", "Проверьте правильность введенных данных, значения меньше или равна 0 недопустимы");
                    DialogError di = new DialogError();
                    di.show(getFragmentManager(),"DialogError");
                    di.setArguments(bundle);
                }
            }
        });


        return view;
    }
}
