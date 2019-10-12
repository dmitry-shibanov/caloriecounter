package com.example.caloriecounter.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.caloriecounter.R;

import java.io.IOException;
import java.io.InputStream;

public class DescriptionProduct extends AppCompatActivity {
    private ImageView mImage;
    private TextView mDescription;
    private TableLayout mIngredients;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_product);

        int index = getIntent().getIntExtra("index",-1);

        mImage = findViewById(R.id.place_image);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(String.valueOf(index));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            // get input stream
            InputStream ims = getAssets().open("pizza.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            mImage.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
    }
}
