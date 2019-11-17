package com.example.caloriecounter.controllers.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.fragments.dialogs.BottomSheetFragment;
import com.example.caloriecounter.controllers.fragments.dialogs.BottomSheetMenuFragment;
import com.example.caloriecounter.controllers.fragments.dialogs.DialogError;
import com.example.caloriecounter.data.DB;
import com.example.caloriecounter.models.Person;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class UserProfile extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button mSave;
    private TextView mBirthday;
    private TextInputEditText mName;
    private TextInputEditText mAimWeight;
    private ImageView mIcon;

    private BottomSheetBehavior sheetBehavior;
    private LinearLayout bottom_sheet;

    private DB db;
    private Person user;


    private OnFragmentInteractionListener mListener;

    public UserProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Bitmap loadImageFromStorage(String path) {

        Bitmap b = null;
        try {
//            File f = new File(path, "profile.jpg");
            File f = new File(path, "profile.jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            mIcon.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            DialogError di = DialogError.newInstance("Изображение не было найдено в файловой системе проверьте не удалили ли вы его", "Проверьте файлы");
            di.show(getFragmentManager(), "DialogError");
        }

        return b;

    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        if(user.getPath()!=null){
            File file = new File(user.getPath());
            if(file.exists()) {
                boolean done = file.delete();
            }
        }
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        UUID uuid = UUID.randomUUID();
        File mypath = new File(directory, uuid.toString() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            user.setPath(mypath.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private String parseDate(Date date) {
        int year = date.getYear() + 1900;

        return date.getDate() + "." + date.getMonth() + "." + year;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Log.i("UserProfile", "requestCode " + requestCode);
        Log.i("UserProfile", "resultCode " + resultCode);
        if (requestCode == 0) {
            Date date = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            mBirthday.setText(parseDate(date));
        }
        if (requestCode == 1) {
//            Uri image = Uri.parse(data.getStringExtra(BottomSheetFragment.ICON_IMAGE));
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), image);
            Bitmap bitmap = (Bitmap) data.getParcelableExtra(BottomSheetFragment.ICON_IMAGE);
            saveToInternalStorage(bitmap);
//                mIcon.setImageURI(image);
            mIcon.setImageBitmap(bitmap);
//            } catch (IOException exp) {
//                DialogError di = DialogError.newInstance("Проверьте точно ли вы выбрали изображение", "Проверьте галлерею или камеру");
//                di.show(getFragmentManager(), "DialogError");
//            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        db = DB.getDB(getContext());
        user = db.getDbHelper().getUser();

        mIcon = (ImageView) view.findViewById(R.id.imageView);
        mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.setTargetFragment(UserProfile.this, 1);
                bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        user.setName("Пользователь 1");

        mBirthday = (TextView) view.findViewById(R.id.textView4);
        mBirthday.setOnClickListener(view1 -> {
            FragmentManager manager = getFragmentManager();
            DateFragment fragment = DateFragment.newInstance(new Date());
            fragment.setTargetFragment(UserProfile.this, 0);
            fragment.show(manager, "DialogDate");
        });

        final Button mMenu = (Button) view.findViewById(R.id.button2);
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetMenuFragment menuFragment = new BottomSheetMenuFragment();
                menuFragment.show(getActivity().getSupportFragmentManager(), menuFragment.getTag());
            }
        });

        mName = (TextInputEditText)view.findViewById(R.id.textInputEditText);
        mAimWeight = (TextInputEditText)view.findViewById(R.id.aim_edit);
        mSave = (Button)view.findViewById(R.id.button);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        initialiaze();


        return view;
    }

    private void initialiaze() {
        if (user != null) {
            if (user.getPath() != null) {
                mIcon.setImageBitmap(loadImageFromStorage(user.getPath()));
            }

            if (user.getBirthday() != null) {
//                mBirthday.setText(parseDate(user.getBirthday()));
            }

            if(user.getName()!=null){
                mName.setText(user.getName());
            }

//            if(user.getaimWeight()!=null){
//                mAimWeight.setText(user.getaimWeight());
//            }
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
