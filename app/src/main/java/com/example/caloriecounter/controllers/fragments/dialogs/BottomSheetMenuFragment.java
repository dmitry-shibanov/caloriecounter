package com.example.caloriecounter.controllers.fragments.dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetMenuFragment extends BottomSheetDialogFragment {
    private MenuListAdapter listAdapter;
    private LinearLayoutManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bottom_sheet_menu_user, container, false);

        listAdapter = new MenuListAdapter();
        manager = new LinearLayoutManager(getContext());
        final RecyclerView mList = (RecyclerView)view.findViewById(R.id.list_menu_items);
        mList.setLayoutManager(manager);
        mList.setAdapter(listAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mList.getContext(),
                manager.getOrientation());
        mList.addItemDecoration(dividerItemDecoration);

        return view;
    }

    class MenuListHolder extends RecyclerView.ViewHolder{

        public MenuListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MenuListAdapter extends RecyclerView.Adapter<MenuListHolder>{

        @NonNull
        @Override
        public MenuListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull MenuListHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
