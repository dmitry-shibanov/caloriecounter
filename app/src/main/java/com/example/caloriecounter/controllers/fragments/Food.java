package com.example.caloriecounter.controllers.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.activities.DescriptionProduct;

import java.io.IOException;
import java.io.InputStream;

public class Food extends Fragment {

//    https://www.flaticon.com/free-icon/burn_1656159#term=calories&page=1&position=16

    private Drawable loadDrawable(int index) {
        try {
            // get input stream
            InputStream ims = getActivity().getAssets().open((index+1)+".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            return d;
        } catch (IOException ex) {
            return null;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView listView = (RecyclerView) view.findViewById(R.id.products_list);

        listView.setAdapter(new ProductsAdapter());
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    class ProductsAdapter extends RecyclerView.Adapter<ProductsHolder> {

        @NonNull
        @Override
        public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_card, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {
            holder.onBind(position);
        }

        @Override
        public int getItemCount() {
            return 8;
        }
    }

    class ProductsHolder extends RecyclerView.ViewHolder {

        public ProductsHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(final int index) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DescriptionProduct.class);
                    intent.putExtra("index", index);
                    startActivity(intent);
                }
            });

            ImageView image = itemView.findViewById(R.id.image_card);
            image.setImageDrawable(loadDrawable(index));
        }


    }
}
