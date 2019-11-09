package com.example.caloriecounter.controllers.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.caloriecounter.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static android.app.Activity.RESULT_OK;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    public static final int PICK_IMAGE = 1;
    public static final String ICON_IMAGE = "IMAGE";

    public BottomSheetFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void sendResult(int resultCode, Uri image){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(ICON_IMAGE,image.toString());

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    Log.i("BottomSheetFragment",selectedImage.getPath());
                    sendResult(Activity.RESULT_OK, selectedImage);
                }
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bottom_sheet, container, false);

        final Button mPhoto = (Button)view.findViewById(R.id.use_photo);
        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        final Button mGallery = (Button)view.findViewById(R.id.use_gallery);
        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , PICK_IMAGE);
            }
        });
        return view;
    }
}
