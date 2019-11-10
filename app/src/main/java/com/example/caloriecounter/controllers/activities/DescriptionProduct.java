package com.example.caloriecounter.controllers.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.data.AppDbHelper;
import com.example.caloriecounter.data.DB;
import com.example.caloriecounter.models.Food;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        DB db = DB.getDB(getApplicationContext());
        AppDbHelper dbHelper = db.getDbHelper();
        List<Food> mFood = dbHelper.getFood();
        Food food = mFood.get(index);
        final TextView mDescription = (TextView)findViewById(R.id.description_product);
        mDescription.setText(food.getContent());
        final TextView mCalories = (TextView)findViewById(R.id.calories_product_description);
        mCalories.setText(food.getCalories());
        mImage = findViewById(R.id.place_image);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(food.getTitle());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            // get input stream
            InputStream ims = getAssets().open("food/"+food.getId()+".jpg");
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
