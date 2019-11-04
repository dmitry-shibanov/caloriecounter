package com.example.caloriecounter.controllers.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.activities.BottomNavigation;
import com.example.caloriecounter.controllers.activities.DescriptionProduct;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Food extends Fragment {

    List<String> real_products = Arrays.asList("Яблоки","Ягоды","Говядина","Бургеры","Шашлык","Пицца","Рыба","Вино");
    List<String> products = real_products.subList(0,real_products.size());
    private Drawable loadDrawable(int index) {
        try {
            InputStream ims = getActivity().getAssets().open((index+1)+".jpg");
            Drawable d = Drawable.createFromStream(ims, null);
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
        final ProductsAdapter prodcutAdapter = new ProductsAdapter();
        listView.setAdapter(prodcutAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((BottomNavigation)getActivity()).setItemListener(query -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                products = real_products.stream().peek(String::toLowerCase).filter((String item)->item.contains(query.toLowerCase())).collect(Collectors.toList());
                products.forEach(item->Log.i("Products-List",item));
                prodcutAdapter.notifyDataSetChanged();
            }else{
                List<String> timedProducts = new ArrayList<>();
                for (String item:
                        real_products) {
                    if(item.toLowerCase().contains(query.toLowerCase())){
                        timedProducts.add(item);
                        Log.i("Products-List",item);
                    }
                }

                products = timedProducts;
                prodcutAdapter.notifyDataSetChanged();
            }
        });

        ((BottomNavigation)getActivity()).ClearSearchView(receipts -> {
            products = real_products.subList(0,real_products.size());
        });

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
            return products.size();
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

            TextView title = itemView.findViewById(R.id.title_card);
            title.setText(products.get(index));
        }


    }
}
