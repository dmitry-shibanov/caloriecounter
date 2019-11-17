package com.example.caloriecounter.controllers.fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.SwipeController;
import com.example.caloriecounter.controllers.SwipeControllerActions;
import com.example.caloriecounter.controllers.activities.BottomNavigation;
import com.example.caloriecounter.controllers.activities.DescriptionProduct;
import com.example.caloriecounter.data.AppDbHelper;
import com.example.caloriecounter.data.DB;
import com.example.caloriecounter.models.Food;
import com.example.caloriecounter.models.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
public class FoodListFragment extends Fragment {

    private List<Food> real_products = null;//Arrays.asList("Яблоки", "Ягоды", "Говядина", "Бургеры", "Шашлык", "Пицца", "Рыба", "Вино");
    private List<Food> products = null;//real_products.subList(0, real_products.size());

    private Drawable loadDrawable(long index) {
        try {
            InputStream ims = getActivity().getAssets().open("food/" + index + ".jpg");
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

        final DB db = DB.getDB(getContext());
        final AppDbHelper appDbHelper = db.getDbHelper();
        real_products = appDbHelper.getFood();
        products = real_products.subList(0, real_products.size());
        final ProductsAdapter prodcutAdapter = new ProductsAdapter();
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.products_list);
        listView.setAdapter(prodcutAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onLeftClicked(int position) {
                Food food = products.get(position);
                Person user = appDbHelper.getUser();

                user.getMMenu().add(food);

                food.setId_food_user(user.getId());

                List<Food> mMenu = user.getMMenu();
                List<Food> myFood = appDbHelper.mMenu();
                List<Food> fal = appDbHelper.getFood();

                System.out.println("ksal");
            }

        },RIGHT);

        listView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(listView);


        ((BottomNavigation) getActivity()).setItemListener(query -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                products = real_products.stream().filter((Food item) -> item.getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                prodcutAdapter.notifyDataSetChanged();
            } else {
                List<Food> timedProducts = new ArrayList<>();
                for (Food item :
                        real_products) {
                    if (item.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        timedProducts.add(item);
                    }
                }

                products = timedProducts;
                prodcutAdapter.notifyDataSetChanged();
            }
        });

        ((BottomNavigation) getActivity()).ClearSearchView(receipts -> {
            products = real_products.subList(0, real_products.size());
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
                    intent.putExtra("index", products.get(index).getId());
                    startActivity(intent);
                }
            });

            ImageView image = itemView.findViewById(R.id.image_card);
            image.setImageDrawable(loadDrawable(products.get(index).getId()));

            TextView title = itemView.findViewById(R.id.title_card);
            title.setText(products.get(index).getTitle());
        }


    }
}
