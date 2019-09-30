package com.example.caloriecounter.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;

public class Food extends Fragment {

//    https://www.flaticon.com/free-icon/burn_1656159#term=calories&page=1&position=16


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products,container,false);

        RecyclerView listView = (RecyclerView)view.findViewById(R.id.products_list);

        listView.setAdapter(new ProductsAdapter());
        listView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    class ProductsAdapter extends RecyclerView.Adapter<ProductsHolder>{

        @NonNull
        @Override
        public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_card,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 11;
        }
    }

    class ProductsHolder extends RecyclerView.ViewHolder{

        public ProductsHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}
