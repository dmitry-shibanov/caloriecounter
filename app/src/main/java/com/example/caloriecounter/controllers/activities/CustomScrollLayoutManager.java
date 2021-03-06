package com.example.caloriecounter.controllers.activities;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Класс наследующийся от LinearLayoutManager, переопределяет логику не дает возможность scroll
 */
public class CustomScrollLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomScrollLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
