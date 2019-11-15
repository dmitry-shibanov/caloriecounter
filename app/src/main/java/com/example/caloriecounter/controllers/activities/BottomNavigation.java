package com.example.caloriecounter.controllers.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.controllers.fragments.Calculate;
import com.example.caloriecounter.controllers.fragments.FoodListFragment;
import com.example.caloriecounter.controllers.fragments.PersonStatistics;
import com.example.caloriecounter.controllers.fragments.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BottomNavigation extends AppCompatActivity {
    private TextView mTextMessage;
    private FragmentManager fragmentManager;

    private onQueryWritten listener;
    private onClearSearchView mOnClearSearchView;

    public interface onQueryWritten {
        void getProducts(String receipts);
    }

    public interface onClearSearchView {
        void getProducts(String receipts);
    }

    public void setItemListener(onQueryWritten listener) {
        this.listener = listener;
    }

    public void ClearSearchView(onClearSearchView listener) {
        this.mOnClearSearchView = listener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_view, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listener.getProducts(s);
                return true;
            }


        });

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {

                return true;
            }
        });

        return true;
    }


    /**
     * реализация listener для выбора загрузки фрагмента на главный экран по нажатию на элемент
     * bottomNavigationBar (bottomNavigationItem).
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new Calculate();
                    replaceFragment(fragment);
                    return true;
                case R.id.navigation_products:
                    fragment = new FoodListFragment();
                    replaceFragment(fragment);
                    return true;
                case R.id.navigation_graphs:
                    fragment = new PersonStatistics();
                    replaceFragment(fragment);
                    return true;
                case R.id.navigation_user:
                    fragment = new UserProfile();
                    replaceFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = new Calculate();
        replaceFragment(fragment);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * @param fragment фрагмент, который ищем в stack и вызываем его или добавляем его на страницу и в stack
     */
    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        boolean fragmentPopped = manager.findFragmentByTag(backStateName) == null ? false : true;
        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_content, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        } else {
            FragmentTransaction ft = manager.beginTransaction();
            boolean check = manager.findFragmentByTag(backStateName) == null ? false : true;
            ft.show(manager.findFragmentByTag(backStateName)).commit();
        }
    }

}

//https://stackoverflow.com/questions/18305945/how-to-resume-fragment-from-backstack-if-exists

