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
import com.example.caloriecounter.controllers.fragments.GrapghFragment1;
import com.example.caloriecounter.controllers.fragments.Graph2;
import com.example.caloriecounter.controllers.fragments.PersonStatistics;
import com.example.caloriecounter.controllers.fragments.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
// https://stackoverflow.com/questions/35497285/how-to-restore-fragment-back-stack-with-in-an-activity-after-application-is-kil
public class BottomNavigation extends AppCompatActivity {
    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private static final String TAG1 = "TAG1";
    private static final String TAG2 = "TAG2";

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
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager manager = getSupportFragmentManager();
        Fragment f = manager.findFragmentById(R.id.frame_content);
        String name = f.getClass().getName();
        if(name.contains("PersonStatistics")){
//            Fragment f1 = manager.findFragmentByTag();
//            Fragment f2 = manager.findFragmentByTag();
//            manager.beginTransaction().remove(f1)
//            manager.beginTransaction().remove(f2);
        }
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
                    Fragment fragment1 = new GrapghFragment1();
                    Fragment fragment2 = new Graph2();

                    fragment = PersonStatistics.newInstance(fragment1, fragment2);
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
            ft.replace(R.id.frame_content, fragment,backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        } else {
            FragmentTransaction ft = manager.beginTransaction();
            boolean check = manager.findFragmentByTag(backStateName) == null ? false : true;
//            manager.beginTransaction().remove(fragment);
//            ft.show(manager.findFragmentByTag(backStateName)).commit();


            ft.replace(R.id.frame_content, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

}

//https://stackoverflow.com/questions/18305945/how-to-resume-fragment-from-backstack-if-exists

