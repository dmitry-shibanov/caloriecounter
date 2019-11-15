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

/**
 * Класс activity для описания блюда, которое заведено в бд.
 */
public class DescriptionProduct extends AppCompatActivity {

    private TableLayout mIngredients;

    private Drawable loadDrawable(String name){
        try {
            InputStream ims = getAssets().open("food/"+name+".jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            return d;
        }
        catch(IOException ex) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_product);

        int id = getIntent().getIntExtra("index",-1);

        DB db = DB.getDB(getApplicationContext());
        AppDbHelper dbHelper = db.getDbHelper();
        Food food = dbHelper.getFoodId(id);

        final TextView mDescription = (TextView)findViewById(R.id.description_product);
        mDescription.setText(food.getContent());
        final TextView mCalories = (TextView)findViewById(R.id.calories_product_description);
        mCalories.setText(food.getCalories());
        ImageView mImage = findViewById(R.id.place_image);
        mImage.setImageDrawable(loadDrawable(String.valueOf(id)));
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(food.getTitle());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
