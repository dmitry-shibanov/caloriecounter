package com.example.caloriecounter.controllers.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caloriecounter.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    public static final int PICK_IMAGE = 1;
    public static final int PICK_IMAGE_CAMERA = 2;
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

    private void sendResult(int resultCode, Bitmap image){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(ICON_IMAGE,image);

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
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        Log.i("BottomSheetFragment",selectedImage.getPath());
                        sendResult(Activity.RESULT_OK, bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PICK_IMAGE_CAMERA:
                Bitmap photo =  (Bitmap)data.getExtras().get("data");
                sendResult(Activity.RESULT_OK, photo);
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
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, PICK_IMAGE_CAMERA);
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
